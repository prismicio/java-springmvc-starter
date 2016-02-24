<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>All documents</title>
        <!-- Required for previews, edit button and experiments -->
        <script src="//static.cdn.prismic.io/prismic.min.js"></script>        
    </head>
    <body>

        <header>
          <h1><a href="/">Your prismic.io project</a></h1>
        </header>

        <form action="/search" method="GET">
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
              <a href="/documents/${document.id}/${document.slug}">
                ${document.slug}
              </a>
            </li>
          </c:forEach>
        </ul>

    </body>
</html>
