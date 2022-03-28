create table ericsson_lte(
    recordType String,
    servedIMSI String,
    sGWAddress String,
    chargingID UInt32,
    servingNodeAddress String,
    accessPointNameNI String,
    pdpPDNType String,
    servedPDPPDNAddress String,
    recordOpeningTime DateTime64,
    duration UInt32,
    causeForRecClosing String,
    recordSequenceNumber UInt16,
    nodeID String,
    localSequenceNumber UInt16,
    servedMSISDN String,
    chargingCharacteristics String,
    servingNodePLMNIdentifier String,
    servedIMEISV String,
    rATType UInt8,
    mSTimeZone String,
    sGWChange UInt8,
    servingNodeType UInt8,
    pGWAddressUsed String,
    pGWPLMNIdentifier String,
    pDNConnectionID String
--     servedPDPPDNAddressExt
--     sGWiPv6Address
--     servingNodeiPv6Address
--     pGWiPv6AddressUsed
--     totalUplinkVolume
--     totalDownlinkVolume
--     serialNumber
) ENGINE = MergeTree()
    order by (servedIMSI)
    partition by toYYYYMMDD(recordOpeningTime);

