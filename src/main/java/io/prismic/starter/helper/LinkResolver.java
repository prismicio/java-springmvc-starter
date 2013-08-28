package io.prismic.starter.helper;

import io.prismic.*;
import io.prismic.starter.helper.*;

public class LinkResolver extends DocumentLinkResolver {

  private PrismicContext prismic;

  public void setPrismicContext(PrismicContext prismic) {
    this.prismic = prismic;
  }

  public String resolve(Fragment.DocumentLink link) {
    return "/documents/" + link.getId() + "/" + link.getSlug() + (prismic.maybeRef() == null ? "" : "?ref=" + prismic.maybeRef());
  }
}