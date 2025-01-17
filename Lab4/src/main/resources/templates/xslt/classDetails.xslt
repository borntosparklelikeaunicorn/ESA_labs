<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Dance Class Information</h2>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Style</th>
                        <th>Level</th>
                        <th>Schedule</th>
                        <th>Studio</th>
                        <th>Delete</th>
                    </tr>

                    <xsl:for-each select="ArrayList/item">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="style"/></td>
                            <td><xsl:value-of select="level"/></td>
                            <td><xsl:value-of select="schedule"/></td>
                            <td><xsl:value-of select="studio/name"/></td>
                            <td>
                                <a href="/classes/delete/{id}" method="DELETE">Delete</a>
                            </td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
