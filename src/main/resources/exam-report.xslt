<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:date="http://exslt.org/dates-and-times"
                extension-element-prefixes="date"
                xmlns:u="java://net.quiz.utils.PdfReportUtil"
                exclude-result-prefixes="u">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/report">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4-portrait" page-height="29.7cm" page-width="24.0cm" margin="1cm">
                    <!--<fo:region-body margin="0 0 0 0" background-position-vertical ="center" background-position-horizontal ="center" background-image="eicc-bg.png" background-position="center" background-repeat="repeat-y"/>-->
                    <fo:region-body margin="0 0 0 0"/>
                    <fo:region-after/>
                    <!--<fo:region-before/>-->
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4-portrait">
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="9pt" font-weight="normal" font-family="Helvetica"
                              border-top="1px solid grey"
                              text-align="center"
                              margin="0mm 10.4mm 0mm 0mm">
                        <fo:block>Trang <fo:page-number/></fo:block>
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body" font-size="10pt">
                    <!--<fo:block width="100pt" margin-bottom="10pt">-->
                    <!--<fo:external-graphic src="eicc_report.png"-->
                    <!--width="100%"-->
                    <!--content-height="50%"-->
                    <!--content-width="scale-to-fit"-->
                    <!--scaling="uniform"/>-->
                    <!--</fo:block>-->
                    <fo:table width="100%" border="1px solid #EAE8E2" margin-bottom="5pt">
                        <fo:table-column column-width="20%">
                        </fo:table-column>
                        <fo:table-column column-width="80%">
                        </fo:table-column>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell text-align="center" border-right="1px solid #EAE8E2" padding-top="5pt">
                                    <fo:block margin-top="10pt">
                                        LOGO
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" padding-top="5pt">
                                    <fo:block margin-top="10pt">
                                        <fo:inline>xxxxxxxx</fo:inline>
                                        <fo:inline font-weight="bold">yyyyyyyyyyy</fo:inline>
                                        <fo:inline>zzzzzzzz</fo:inline>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell border-right="1px solid #EAE8E2">
                                    <fo:block>&#160;</fo:block>
                                </fo:table-cell>
                                <fo:table-cell>
                                    <fo:block>&#160;</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row margin-bottom="10pt">
                                <fo:table-cell border-right="1px solid #EAE8E2" padding-bottom="5pt">
                                    <fo:block color="red" text-align="center" font-size="8pt">
                                        IMG LOG
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center" font-size="8pt" padding-bottom="5pt">
                                    <fo:block>
                                        abc
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                    <fo:table width="100%" font-size="8pt">
                        <fo:table-column column-width="100%">
                        </fo:table-column>
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell font-size="12pt"
                                               border-bottom="1px solid #EAE8E2" padding="5pt">
                                    <xsl:for-each select="item">
                                        <fo:block font-weight="bold"
                                                  padding-top="10pt" padding-bottom="5pt"
                                                  border-bottom="1px solid grey">
                                            <xsl:value-of select="sectionId"/>
                                            <xsl:value-of select="sectionName"/>
                                        </fo:block>
                                        <xsl:if test="type = 'OPTION_FROM_GIVEN'">
                                            <fo:block border-left="1px solid grey"
                                                      border-right="1px solid grey"
                                                      border-bottom="1px solid grey">
                                                <fo:table width="100%" font-size="12pt">
                                                    <fo:table-column column-width="1%">
                                                    </fo:table-column>
                                                    <fo:table-column column-width="49%">
                                                    </fo:table-column>
                                                    <fo:table-column column-width="1%">
                                                    </fo:table-column>
                                                    <fo:table-column column-width="49%">
                                                    </fo:table-column>
                                                    <fo:table-body>
                                                        <fo:table-row>
                                                            <fo:table-cell>
                                                                <fo:block>&#160;</fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell>
                                                                <xsl:if test="not(option1)">
                                                                    <fo:block>&#160;</fo:block>
                                                                </xsl:if>
                                                                <xsl:if test="option1">
                                                                    <fo:list-block>
                                                                        <xsl:for-each select="option1">
                                                                            <fo:list-item>
                                                                                <fo:list-item-label>
                                                                                    <fo:block>&#160;</fo:block>
                                                                                </fo:list-item-label>
                                                                                <fo:list-item-body>
                                                                                    <fo:block padding="3pt">
                                                                                        <xsl:value-of select="."/>
                                                                                    </fo:block>
                                                                                </fo:list-item-body>
                                                                            </fo:list-item>
                                                                        </xsl:for-each>
                                                                    </fo:list-block>
                                                                </xsl:if>
                                                            </fo:table-cell>
                                                            <fo:table-cell>
                                                                <fo:block>&#160;</fo:block>
                                                            </fo:table-cell>
                                                            <fo:table-cell>
                                                                <xsl:if test="not(option2)">
                                                                    <fo:block>&#160;</fo:block>
                                                                </xsl:if>
                                                                <xsl:if test="option2">
                                                                    <fo:list-block>
                                                                        <xsl:for-each select="option2">
                                                                            <fo:list-item>
                                                                                <fo:list-item-label>
                                                                                    <fo:block>&#160;</fo:block>
                                                                                </fo:list-item-label>
                                                                                <fo:list-item-body>
                                                                                    <fo:block padding="3pt">
                                                                                        <xsl:value-of select="."/>
                                                                                    </fo:block>
                                                                                </fo:list-item-body>
                                                                            </fo:list-item>
                                                                        </xsl:for-each>
                                                                    </fo:list-block>
                                                                </xsl:if>
                                                            </fo:table-cell>
                                                        </fo:table-row>
                                                    </fo:table-body>
                                                </fo:table>
                                            </fo:block>
                                        </xsl:if>
                                        <xsl:for-each select="question">
                                            <fo:block padding-top="5pt" padding-bottom="5pt">
                                                <xsl:value-of select="type"/>
                                                <xsl:value-of select="text"/>
                                            </fo:block>

                                            <!--<xsl:variable name="textArr">-->
                                                <!--<xsl:value-of select="u:split(text)" />-->
                                            <!--</xsl:variable>-->
                                            <!--<xsl:for-each select="$textArr">-->
                                                <!--<fo:block>-->
                                                    <!--<xsl:value-of select="."/>-->
                                                <!--</fo:block>-->
                                            <!--</xsl:for-each>-->

                                            <xsl:if test="type = 'TEXT'">
                                                <fo:block>&#160;</fo:block>
                                                <fo:block>&#160;</fo:block>
                                            </xsl:if>
                                        </xsl:for-each>
                                    </xsl:for-each>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
