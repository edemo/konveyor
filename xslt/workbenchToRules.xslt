<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" version="1.0" encoding="utf-8" indent="yes" omit-xml-declaration="yes"/>

  <xsl:template match="/">
		<ruleset>
    <xsl:apply-templates select="//node[@class='net.sourceforge.pmd.util.fxdesigner.RuleEditorsController']"/>
		</ruleset>
  </xsl:template>

  <xsl:template match="node[@class='net.sourceforge.pmd.util.fxdesigner.RuleEditorsController']">
    <rule>
			<xsl:attribute name="name">
				<xsl:copy-of select=".//property[@property-name='name']/value/@value"/>
			</xsl:attribute>
			<xsl:attribute name="language">
				<xsl:copy-of select=".//property[@property-name='language']/value/@value"/>
			</xsl:attribute>
			<xsl:attribute name="class">
				<xsl:copy-of select=".//property[@property-name='clazz']/value/@value"/>
			</xsl:attribute>
			<xsl:attribute name="message">
				<xsl:copy-of select=".//property[@property-name='message']/value/@value"/>
			</xsl:attribute>
			<priority>
				<xsl:value-of select=".//property[@property-name='priority']/value/@value"/>
			</priority>
			<description>
				<xsl:value-of select="string-join(.//property[@property-name='description']/value/@value[string-length()>0],'; ')"/>
			</description>
			<properties>
				<property name="xpath">
					<value>
				<xsl:value-of select=".//property[@property-name='xpathExpression']/value/@value"/>
					</value>
				</property>
			</properties>
    </rule>
  </xsl:template>

  <xsl:template match="@*|*|processing-instruction()|comment()">
    <xsl:copy>
      <xsl:apply-templates select="*|@*|text()|processing-instruction()|comment()"/>
    </xsl:copy>
  </xsl:template>

</xsl:stylesheet>

