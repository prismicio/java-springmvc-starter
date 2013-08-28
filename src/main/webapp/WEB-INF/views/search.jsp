<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Search results</title>
    </head>
    <body>

        <c:set var="ref" scope="page" value="${(empty prismic.maybeRef()) ? '' : '?ref='.concat(prismic.maybeRef())}"/>

        <header>
          <jsp:include page="toolbar.jsp" />

          <a href="/${ref}">
            <h1>Your prismic.io project</h1>
          </a>

        </header>

        <h1>
          <c:choose>
            <c:when test="${results.size() == 0}">
              No results found
            </c:when>
            <c:when test="${results.size() == 1}">
              One document found
            </c:when>
            <c:otherwise>
              ${results.size()} documents found
            </c:otherwise>
          </c:choose>
        </h1>

        <ul>
          <c:forEach items="${results}" var="document">
            <li>
              <a href="${prismic.linkResolver.resolveDocument(document)}">${document.slug}</a>
            </li>
          </c:forEach>
        </ul>

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
