<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Document detail - ${document.slug}</title>
    </head>
    <body>

        <c:set var="ref" scope="page" value="${(empty prismic.maybeRef()) ? '' : '?ref='.concat(prismic.maybeRef())}"/>

        <header>
          <jsp:include page="toolbar.jsp" />

          <a href="/${ref}">
            <h1>Your prismic.io project</h1>
          </a>

        </header>

        <article id="${document.id}">
          ${document.asHtml(prismic.linkResolver)}
        </article>

        <p>
          <a href="/${ref}">Back to home</a>
        </p>

        <footer>
          <c:if test="${!prismic.hasPrivilegedAccess()}">
            <hr> <a href="/signin">Sign in to preview changes</a>
          </c:if>
        </footer>

    </body>
</html>
