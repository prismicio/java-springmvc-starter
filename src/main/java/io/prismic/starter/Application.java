package io.prismic.starter;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.*;

import io.prismic.*;

@Controller
public class Application {

    public static LinkResolver linkResolver = new LinkResolver();

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class DocumentNotFoundException extends RuntimeException {
        private static final long serialVersionUID = -1656797987231329273L;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("api") Api api, ModelMap model) {
        model.addAttribute("documents", api.query().submit().getResults());
        return "index";
    }

    @RequestMapping(value = "/documents/{id:[-_a-zA-Z0-9]{16}}/{slug}", method = RequestMethod.GET)
    public String detail(@ModelAttribute("api") Api api, @PathVariable("id") String id,
            @PathVariable("slug") String slug, ModelMap model) {
        Document maybeDocument = api.getByID(id);
        if (maybeDocument == null) {
            throw new DocumentNotFoundException();
        }
        if (maybeDocument.getSlug().equals(slug)) {
            model.addAttribute("document", maybeDocument);
            return "detail";
        } else {
            return "redirect:/documents/" + id + "/" + maybeDocument.getSlug() + "/";
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@ModelAttribute("api") Api api, @RequestParam(value = "q", required = false) String q,
            ModelMap model) {
        List<Document> results = new ArrayList<Document>();
        if (q != null && !q.trim().isEmpty()) {
            results = api.query("[[:d = fulltext(document, \"" + q + "\")]]").submit().getResults();
        }
        model.addAttribute("q", q);
        model.addAttribute("results", results);
        return "search";
    }

    @RequestMapping(path = "/preview")
    public String preview(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("api") Api api,
            @RequestParam("token") String token) {
        String fallbackUrl = request.getServletPath();
        String url = api.previewSession(token, linkResolver, fallbackUrl);
        Cookie previewCookie = new Cookie(Prismic.PREVIEW_COOKIE, token);
        previewCookie.setMaxAge(1800);
        response.addCookie(previewCookie);
        return "redirect:" + url;
    }

    @ModelAttribute("api")
    public Api getApi(HttpServletRequest request) {
        return (Api) request.getAttribute("prismicapi");
    }

}
