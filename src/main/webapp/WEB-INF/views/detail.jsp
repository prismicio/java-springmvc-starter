<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Document detail - ${document.slug}</title>
        <!-- Required for previews, edit button and experiments -->
        <script src="//static.cdn.prismic.io/prismic.min.js"></script>        
    </head>
    <body>

        <header>
          <h1><a href="/">Your prismic.io project</a></h1>
        </header>

        <article id="${document.id}">
          ${document.asHtml(prismic.linkResolver)}
        </article>

        <p>
          <a href="/">Back to home</a>
        </p>

    </body>
</html>
