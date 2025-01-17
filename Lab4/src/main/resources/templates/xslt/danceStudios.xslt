<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Dance Studios</h2>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Delete</th>
                    </tr>
                    <xsl:for-each select="ArrayList/item">
                        <tr>
                            <td><a href="/studios/{id}"><xsl:value-of select="id"/></a></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="address"/></td>
                            <td><xsl:value-of select="phone"/></td>
                            <td>
                                <a href="/studios/delete/{id}" method="DELETE">Delete</a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

                <h1>Add Dance Studio</h1>
                <form href="/studios/create" method="post" enctype="application/x-www-form-urlencoded">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required="required"/><br/>

                    <label for="address">Address:</label>
                    <input type="text" id="address" name="address" required="required"/><br/>

                    <label for="phone">Phone:</label>
                    <input type="text" id="phone" name="phone" required="required"/><br/>

                    <input type="submit" value="Submit"/>
                </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
