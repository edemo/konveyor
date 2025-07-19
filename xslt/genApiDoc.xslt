<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:output method="xml" version="1.0"
		doctype-public="-//OASIS//DTD DocBook XML V4.5//EN"
		doctype-system="http://www.oasis-open.org/docbook/xml/4.5/docbookx.dtd"
		encoding="utf-8" indent="yes" />

	<xsl:variable name="testcases">
		<xsl:apply-templates
			select="//Annotation[@SimpleName= 'DisplayName']" />
	</xsl:variable>

	<xsl:template match="/">
		<article>
			<title>
				Api docs
			</title>
			<section>
				<title id="Data_Structures">Data Structures</title>
				<section>
					<xsl:apply-templates
						select="//RecordDeclaration" />
				</section>
			</section>
			<section>
				<title>Delegates</title>
				<section>
					<xsl:apply-templates
						select="//ClassDeclaration[./ModifierList/Annotation[@SimpleName = 'Delegate']]" />
				</section>
			</section>
			<section>
				<title id="Services">Services</title>
				<section>
					<xsl:apply-templates
						select="//ClassDeclaration[./ModifierList/Annotation[@SimpleName = 'Service']]" />
				</section>
			</section>
			<section>
				<title>Repositories</title>
				<section>
					<xsl:apply-templates
						select="//ClassDeclaration[./ModifierList/Annotation[@SimpleName = 'Repository']]" />
				</section>
			</section>
			<section>
				<title>Utilities</title>
				<section>
					<xsl:apply-templates
						select="//ClassDeclaration[matches(@SimpleName,'^.*Util$')]" />
				</section>
			</section>
			<section>
				<title>Glue</title>
				<section>
					<xsl:apply-templates
						select="//ClassDeclaration[
						./ModifierList/Annotation[@SimpleName = 'Glue']
						]" />
				</section>
			</section>
		</article>
	</xsl:template>

	<xsl:template match="//ClassDeclaration" priority="0">
		<section>
			<title>
				<xsl:attribute name="id" select="@SimpleName" />
				<xsl:value-of select="@SimpleName" />
			</title>
			<section>
				<title>API</title>
				<itemizedlist>
					<xsl:for-each select=".//MethodDeclaration">
						<listitem>
							<xsl:apply-templates select="." />
						</listitem>
					</xsl:for-each>
				</itemizedlist>
			</section>
			<xsl:if
				test=".//FieldDeclaration[not(./ModifierList/Annotation/@SimpleName = 'Autowired')]">
				<section>
					<title>fields</title>
					<itemizedlist>
						<xsl:for-each
							select=".//FieldDeclaration[not(./ModifierList/Annotation/@SimpleName = 'Autowired')]">
							<listitem>
								<xsl:apply-templates select="." />
							</listitem>
						</xsl:for-each>
					</itemizedlist>
				</section>
			</xsl:if>
			<xsl:if
				test=".//FieldDeclaration[./ModifierList/Annotation/@SimpleName = 'Autowired']">
				<section>
					<title>dependencies</title>
					<itemizedlist>
						<xsl:for-each
							select=".//FieldDeclaration[./ModifierList/Annotation/@SimpleName = 'Autowired']">
							<listitem>
								<xsl:apply-templates select="." />
							</listitem>
						</xsl:for-each>
					</itemizedlist>
				</section>

			</xsl:if>
		</section>
	</xsl:template>

	<xsl:template
		match="//ClassDeclaration[./ModifierList/Annotation[@SimpleName = 'Delegate']]">
		<section>
			<title>
				<xsl:value-of select="@SimpleName" />
			</title>
			<section>
				<title>API</title>
				<itemizedlist>
					<xsl:for-each select=".//MethodDeclaration">
						<listitem>
							<xsl:apply-templates select="." />
						</listitem>
					</xsl:for-each>
				</itemizedlist>
			</section>
			<section>
				<title>dependencies</title>
				<itemizedlist>
					<xsl:for-each
						select=".//FieldDeclaration[./ModifierList/Annotation/@SimpleName = 'Autowired']">
						<listitem>
							<xsl:apply-templates select="." />
						</listitem>
					</xsl:for-each>
				</itemizedlist>
			</section>
		</section>
	</xsl:template>

	<xsl:template
		match="//ClassDeclaration[./ModifierList/Annotation[@SimpleName = 'Service']]">
		<section>
			<title>
				<xsl:value-of select="@SimpleName" />
			</title>
			<section>
				<title>Description</title>
				<xsl:for-each
					select="$testcases/testSuite[tested/@SimpleName= current()/@SimpleName]/testcase">
					<para>
						<xsl:value-of select="@description" />
					</para>
				</xsl:for-each>
			</section>
			<section>
				<title>API</title>
				<itemizedlist>
					<xsl:for-each
						select=".//MethodDeclaration[@Name='apply']">
						<listitem>
							<xsl:apply-templates select="." />
						</listitem>
					</xsl:for-each>
				</itemizedlist>
			</section>
			<section>
				<title>dependencies</title>
				<itemizedlist>
					<xsl:for-each
						select=".//FieldDeclaration[./ModifierList/Annotation/@SimpleName = 'Autowired']">
						<listitem>
							<xsl:apply-templates select="." />
						</listitem>
					</xsl:for-each>
				</itemizedlist>
			</section>
		</section>
	</xsl:template>

	<xsl:template
		match="FieldDeclaration[./ModifierList/Annotation/@SimpleName = 'Autowired']">
		<xsl:apply-templates select="./ClassType" />
	</xsl:template>

	<xsl:template
		match="FieldDeclaration[
		not(./ModifierList/Annotation/@SimpleName = 'Autowired')
		]">
		<xsl:value-of select="./ModifierList/@EffectiveModifiers" />
		<xsl:text> </xsl:text>
		<xsl:apply-templates select="./ClassType" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="./VariableDeclarator/@Name" />
	</xsl:template>

	<xsl:template match="VariableDeclarator">

	</xsl:template>


	<xsl:template
		match="//ClassDeclaration[./ModifierList/Annotation[@SimpleName = 'Repository']]">
		<section>
			<title>
				<xsl:value-of select="@SimpleName" />
			</title>
			<para>
				<itemizedlist>
					<listitem>
						extends:
						<xsl:value-of
							select="ExtendsList/ClassType/@SimpleName" />
					</listitem>
					<listitem>
						stores:
						<xsl:apply-templates
							select="ExtendsList/ClassType/TypeArguments/ClassType[1]" />
					</listitem>
					<listitem>
						id:
						<xsl:apply-templates
							select="ExtendsList/ClassType/TypeArguments/ClassType[2]" />
					</listitem>
				</itemizedlist>
			</para>
			<section>
				<title>Query definitions</title>
				<itemizedlist>
					<xsl:for-each select=".//MethodDeclaration">
						<listitem>
							<xsl:apply-templates select="." />
						</listitem>
					</xsl:for-each>
				</itemizedlist>
			</section>
		</section>
	</xsl:template>

	<xsl:template match="MethodDeclaration">
		<para>
			<xsl:apply-templates select="./ClassType" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="@Name" />
			<xsl:apply-templates select="./FormalParameters" />
		</para>
	</xsl:template>

	<xsl:template match="FormalParameters">
		<xsl:variable name="components">
			<xsl:apply-templates select="FormalParameter" />
		</xsl:variable>
		<xsl:value-of
			select="'('||string-join($components,',')||')'" />
	</xsl:template>

	<xsl:template match="FormalParameter">
		<xsl:apply-templates select="./ClassType" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="./VariableId/@Name" />
	</xsl:template>


	<xsl:template match="RecordDeclaration">
		<section>
			<title>
				<xsl:value-of select="@SimpleName" />
			</title>
			<itemizedlist>
				<xsl:apply-templates select=".//RecordComponent" />
			</itemizedlist>
		</section>
	</xsl:template>

	<xsl:template match="RecordComponent">
		<listitem>
			<xsl:apply-templates select="./ClassType" />
			<xsl:text> </xsl:text>
			<xsl:value-of select="./VariableId/@Name" />
		</listitem>
	</xsl:template>

	<xsl:template match="ClassType">
		<xsl:value-of select="@SimpleName" />
		<xsl:apply-templates select="TypeArguments" />
	</xsl:template>

	<xsl:template match="TypeArguments">
		<xsl:variable name="components">
			<xsl:apply-templates select="ClassType" />
		</xsl:variable>
		<xsl:value-of
			select="'&lt;'||string-join($components,',')||'&gt;'" />
	</xsl:template>

	<xsl:template
		match="Annotation[@SimpleName= 'DisplayName']">
		<testSuite>
			<xsl:copy-of
				select="ancestor::ClassDeclaration/@SimpleName" />
			<xsl:for-each
				select="ancestor::ClassDeclaration//FieldDeclaration[//Annotation[
			@SimpleName='Autowired' or
			@SimpleName='InjectMocks'
			]]/ClassType/@SimpleName
		">
				<tested>
					<xsl:copy-of select="." />
				</tested>
			</xsl:for-each>
			<testcase>
				<xsl:attribute name="description"
					select=".//StringLiteral/@ConstValue" />
			</testcase>
		</testSuite>
	</xsl:template>

	<xsl:template
		match="@*|*|processing-instruction()|comment()">
		<xsl:copy>
			<xsl:apply-templates
				select="*|@*|text()|processing-instruction()|comment()" />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>