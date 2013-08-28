package io.prismic.starter.helper;

import java.util.*;

import javax.annotation.*;
import javax.servlet.http.*;

import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.*;

import io.prismic.*;

/** 
 * A Prismic context that help to keep the reference 
 * to useful primisc.io contextual data
 */
public class PrismicContext {

  // -- Define the key name to use for storing the Prismic.io access token into the Play session
  public final static String ACCESS_TOKEN = "ACCESS_TOKEN";

  private PrismicConfig config;
  private Cache cache;
  private Logger logger;
  private Api api;
  private String ref;
  private DocumentLinkResolver linkResolver;
  private String accessToken;

  @Autowired
  private HttpSession session;

  @Autowired
  private HttpServletRequest request;
 
  public void setCache(Cache cache) {
    this.cache = cache;
  }

  public void setConfig(PrismicConfig config) {
    this.config = config;
  }

  public void setLogger(Logger logger) {
    this.logger = logger;
  }

  public void setLinkResolver(DocumentLinkResolver linkResolver) {
    this.linkResolver = linkResolver;
  }

  public Api getApi() {
    return api;
  }

  public DocumentLinkResolver getLinkResolver() {
    return linkResolver;
  }

  public String getRef() {
    return ref;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String maybeRef() {
    if(ref.equals(api.getMaster().getRef())) {
      return null;
    }
    return ref;
  }

  public boolean hasPrivilegedAccess() {
    return accessToken != null;
  }

  @PostConstruct
  private void init() {

    // Override the common accessToken with the one in Session if provided
    this.accessToken = config.getAccessToken();
    if(session.getAttribute(ACCESS_TOKEN) != null) {
      this.accessToken = (String)session.getAttribute(ACCESS_TOKEN);
    }

    // Retrieve the API
    this.api = Api.get(config.getApiEndpoint(), accessToken, cache, logger);

    // Ref
    this.ref = request.getParameter("ref");
    if(this.ref == null || this.ref.trim().isEmpty()) {
      this.ref = this.api.getMaster().getRef();
    }

  }

  // -- Helper: Retrieve a single document by Id
  public Document getDocument(String id) {
    List<Document> results = this.getApi().getForm("everything").query("[[:d = at(document.id, \"" + id + "\")]]").ref(this.getRef()).submit();
    if(results.size() > 0) {
      return results.get(0);
    }
    return null;
  }

  // -- Helper: Retrieve several documents by Id
  public List<Document> getDocuments(List<String> ids) {
    if(ids.isEmpty()) {
      return new ArrayList<Document>();
    } else {
      StringBuilder q = new StringBuilder();
      q.append("[[:d = any(document.id, [");
      String sep = "";
      for(String id: ids) {
        q.append(sep + "\"" + id + "\"");
        sep = ",";
      }
      q.append("\"]]");
      return this.getApi().getForm("everything").query(q.toString()).ref(this.getRef()).submit();
    }
  }

  // -- Helper: Retrieve a single document from its bookmark
  public Document getBookmark(String bookmark) {
    String id = this.getApi().getBookmarks().get(bookmark);
    if(id != null) {
      return getDocument(id);
    } else {
      return null;
    }
  }

  // -- Helper: Check if the slug is valid and return to the most recent version to redirect to if needed, or return DOCUMENT_NOT_FOUND if there is no match
  public static final String DOCUMENT_NOT_FOUND = "DOCUMENT_NOT_FOUND".intern();
  
  public String checkSlug(Document document, String slug) {
    if(document != null) {
      if(document.getSlug().equals(slug)) {
        return null;
      }
      if(document.getSlugs().contains(slug)) {
        return document.getSlug();
      }
    }
    return DOCUMENT_NOT_FOUND;
  }


}