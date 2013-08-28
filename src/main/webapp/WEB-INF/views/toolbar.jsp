<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${prismic.hasPrivilegedAccess()}">

  <div id="toolbar">

    <form method="GET">
      <label for="releaseSelector">See this website: </label>
      <select id="releaseSelector" name="ref" onchange="this.form.submit()">
        <c:choose>
          <c:when test="${prismic.ref == prismic.api.master.ref}">
            <option value="" selected="selected">As currently seen by guest visitors</option>
          </c:when>
          <c:otherwise>
            <option value="">As currently seen by guest visitors</option>
          </c:otherwise>
        </c:choose>
        <optgroup label="Or preview the website in a future release:">
          <c:forEach items="${prismic.api.refs}" var="ref">
            <c:choose>
              <c:when test="${ref.isMasterRef()}">
              </c:when>
              <c:when test="${ref.ref == prismic.ref}">
                <option value="${ref.ref}" selected="selected">As ${ref.label}</option>
              </c:when>
              <c:otherwise>
                 <option value="${ref.ref}">As ${ref.label}</option>
              </c:otherwise>
            </c:choose>
          </c:forEach>
        </optgroup>
      </select>
    </form>

    <form action="/signout" method="POST">
      <input type="submit" value="Disconnect">
    </form>

    <hr>

  </div>

</c:if>