<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Dance Classes</h2>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Style</th>
                        <th>Level</th>
                        <th>Schedule</th>
                        <th>Studio</th>
                    </tr>
                    <xsl:for-each select="ArrayList/item">
                        <tr>
                            <td><a href="/classes/{id}"><xsl:value-of select="id"/></a></td>
                            <td><xsl:value-of select="style"/></td>
                            <td><xsl:value-of select="level"/></td>
                            <td><xsl:value-of select="schedule"/></td>
                            <td><xsl:value-of select="studio_id"/></td>
                            <td>
                                <a href="/classes/delete/{id}" method="DELETE">Delete</a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

                <h1>Add Dance Class</h1>
                <form action="/classes/create" method="POST" enctype="application/x-www-form-urlencoded">
                    <label for="style">Style:</label>
                    <input type="text" id="style" name="style" required="required"/><br/>

                    <label for="level">Level:</label>
                    <input type="text" id="level" name="level" required="required"/><br/>

                    <label for="schedule">Schedule:</label>
                    <input type="text" id="schedule" name="schedule" required="required"/><br/>

                    <label for="studio_id">Studio ID:</label>
                    <input type="number" id="studio_id" name="studio_id" required="required"/><br/>

                    <input type="submit" value="Submit"/>
                </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
