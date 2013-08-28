package io.prismic.starter.web;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.ui.*;

import io.prismic.*;
import io.prismic.starter.helper.*;

@Controller
public class Application {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public static class DocumentNotFoundException extends RuntimeException {
  }

  @Autowired
  private PrismicContext prismic;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(@RequestParam(value="ref", required=false) String ref, ModelMap model) {
    model.addAttribute("documents", prismic.getApi().getForm("everything").ref(prismic.getRef()).submit());
    model.addAttribute("prismic", prismic);
    return "index";
  }

  @RequestMapping(value="/documents/{id:[-_a-zA-Z0-9]{16}}/{slug}", method = RequestMethod.GET)
  public String detail(@PathVariable("id") String id, @PathVariable("slug") String slug, @RequestParam(value="ref", required=false) String ref, ModelMap model) {
    Document maybeDocument = prismic.getDocument(id);
    String checked = prismic.checkSlug(maybeDocument, slug);
    if(checked == null) {
      model.addAttribute("document", maybeDocument);
      model.addAttribute("prismic", prismic);
      return "detail";
    }
    else if(checked == PrismicContext.DOCUMENT_NOT_FOUND) {
      throw new DocumentNotFoundException();
    }
    else {
      return "redirect:/documents/" + id + "/" + checked + "/" + (prismic.maybeRef() == null ? "" : "?ref=" + prismic.maybeRef());
    }
  }

  @RequestMapping(value="/search", method = RequestMethod.GET)
  public String search(@RequestParam(value="q", required=false) String q, @RequestParam(value="ref", required=false) String ref, ModelMap model) {
    List<Document> results = new ArrayList<Document>();
    if(q != null && !q.trim().isEmpty()) {
      results = prismic.getApi().getForm("everything").query("[[:d = fulltext(document, \"" + q + "\")]]").ref(prismic.getRef()).submit();
    }
    model.addAttribute("q", q);
    model.addAttribute("results", results);
    model.addAttribute("prismic", prismic);
    return "search";
  }

}