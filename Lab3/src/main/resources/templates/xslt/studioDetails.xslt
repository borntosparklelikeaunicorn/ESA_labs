<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">

        <html>
            <body>
                <h2>Studio Information</h2>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Delete</th>
                    </tr>

                    <!-- Применяем xsl:for-each для всех студий -->
                    <xsl:for-each select="List12/item">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="address"/></td>
                            <td><xsl:value-of select="phone"/></td>
                            <td>
                                <!-- Ссылка на удаление элемента -->
                                <a href="/api/studios/delete/{id}" method="DELETE">Delete</a>
                            </td>
                        </tr>
                    </xsl:for-each>

                </table>
            </body>
        </html>

    </xsl:template>
</xsl:stylesheet>