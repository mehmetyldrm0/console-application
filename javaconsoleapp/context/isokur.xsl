<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes" omit-xml-declaration="no" />

    <xsl:template match="/">
        <Data Date="{Tarih_Date/@Date}">
            <Forex>
                <xsl:apply-templates select="//Currency[ForexBuying]"/>
            </Forex>
            <Banknote>
                <xsl:apply-templates select="//Currency[BanknoteBuying]"/>
            </Banknote>
            <Cross>
                <xsl:apply-templates select="//Currency[CrossRateUSD]"/>
            </Cross>
            <Information>
                <xsl:apply-templates select="//Currency[@Kod='XDR']"/>
            </Information>
        </Data>
    </xsl:template>

    <xsl:template match="Currency[ForexBuying]">
        <Currency Pair="{@Kod}/TRY" Unit="{Unit}" Rate="{ForexBuying}"/>
    </xsl:template>

    <xsl:template match="Currency[BanknoteBuying]">
        <Currency Pair="{@Kod}/TRY" Unit="{Unit}" Rate="{BanknoteBuying}"/>
    </xsl:template>

    <xsl:template match="Currency[CrossRateUSD]">
        <Currency Pair="{@Kod}/USD" Unit="{Unit}" Rate="{CrossRateUSD}"/>
    </xsl:template>
   
    <xsl:template match="Currency[@Kod='XDR']">
        <Currency Pair="{@Kod}/USD" Unit="{Unit}" Rate="{ForexBuying}"/>
        <Currency Pair="{@Kod}/TRY" Unit="{Unit}" Rate="{CrossRateOther}"/>
    </xsl:template>

</xsl:stylesheet>
