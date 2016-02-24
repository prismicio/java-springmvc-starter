<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Search results</title>
        <!-- Required for previews, edit button and experiments -->
        <script src="//static.cdn.prismic.io/prismic.min.js"></script>        
    </head>
    <body>

        <header>
          <h1><a href="/">Your prismic.io project</a></h1>
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
          <a href="/">Back to home</a>
        </p>

    </body>
</html>
