<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>All documents</title>
    </head>
    <body>

        <c:set var="ref" scope="page" value="${(empty prismic.maybeRef()) ? '' : '?ref='.concat(prismic.maybeRef())}"/>

        <header>
          <jsp:include page="toolbar.jsp" />

          <a href="/${ref}">
            <h1>Your prismic.io project</h1>
          </a>

        </header>

        <form action="/search${ref}" method="GET">
          <input type="text" name="q" value="">
          <input type="submit" value="Search">
        </form>

        <hr>

        <h2>
          <c:choose>
            <c:when test="${documents.size() == 0}">
              No documents found
            </c:when>
            <c:when test="${documents.size() == 1}">
              One document found
            </c:when>
            <c:otherwise>
              ${documents.size()} documents found
            </c:otherwise>
          </c:choose>
        </h2>

        <ul>
          <c:forEach items="${documents}" var="document">
            <li>
              <a href="/documents/${document.id}/${document.slug}${ref}">
                ${document.slug}
              </a>
            </li>
          </c:forEach>
        </ul>

        <footer>
          <c:if test="${!prismic.hasPrivilegedAccess()}">
            <hr> <a href="/signin">Sign in to preview changes</a>
          </c:if>
        </footer>

    </body>
</html>
