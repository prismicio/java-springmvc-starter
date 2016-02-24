package io.prismic.starter;

import io.prismic.*;

public class LinkResolver extends SimpleLinkResolver {

    public String resolve(Fragment.DocumentLink link) {
        return "/documents/" + link.getId() + "/" + link.getSlug();
    }

}
