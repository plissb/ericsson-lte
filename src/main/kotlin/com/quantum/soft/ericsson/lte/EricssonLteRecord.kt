package com.quantum.soft.ericsson.lte

import ru.cwt.asn1.ericsson.sgw_r9.*
import java.sql.PreparedStatement
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

data class EricssonLteRecord(
    val recordType: String,
    val servedIMSI: String,
    val sGWAddress: String,
    val chargingID: Long,
    val servingNodeAddress: String,
    val accessPointNameNI: String,
    val pdpPDNType: String,
    val servedPDPPDNAddress: String,
    val dataVolumeGPRSUplink: Long,
    val dataVolumeGPRSDownlink: Long,
    val recordOpeningTime: LocalDateTime,
    val duration: Long,
    val causeForRecClosing: String,
    val recordSequenceNumber: Long,
    val nodeID: String,
    val localSequenceNumber: Long,
    val servedMSISDN: String,
    val chargingCharacteristics: String,
    val servingNodePLMNIdentifier: String,
    val servedIMEISV: String,
    val rATType: Long,
    val mSTimeZone: String,
    val sGWChange: Long,
    val servingNodeType: Long,
    val pGWAddressUsed: String,
    val pGWPLMNIdentifier: String,
    val pDNConnectionID: String
) {

    companion object {

        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyMMddHHmmss")

        private fun gsnAddressToString(addr: GSNAddress): String {
            if(addr.ipBinaryAddress != null)
                return iPBinaryAddressToString(addr.ipBinaryAddress)
            if(addr.ipTextRepresentedAddress != null)
                return iPTextRepresentedAddressToString(addr.ipTextRepresentedAddress)
            return "<None>"
        }

        private fun ipAddressToString(addr: IPAddress): String {
            if(addr.ipBinaryAddress != null)
                return iPBinaryAddressToString(addr.ipBinaryAddress)
            if(addr.ipTextRepresentedAddress != null)
                return iPTextRepresentedAddressToString(addr.ipTextRepresentedAddress)
            return "<None>"
        }

        private fun iPBinaryAddressToString(addr: IPBinaryAddress): String {
            if(addr.ipBinV4Address != null)
                return addr.ipBinV4Address.value
                    .map { it.toInt() and 0xFF }
                    .map{ it.toString(10) }
                    .reduce{ v: String, acc: String -> if(acc.isNotEmpty()) "$acc.$v" else v }
            if (addr.ipBinV6Address != null)
                return addr.ipBinV6Address.value
                    .map{ it.toInt() and 0xFF}
                    .map { it.toString(16) }
                    .reduce{ v,a -> if(a.isNotEmpty()) "$a.$v" else v }
            return "<None>"
        }

        private fun iPTextRepresentedAddressToString(addr: IPTextRepresentedAddress): String {
            if(addr.ipTextV4Address != null)
                return addr.ipTextV4Address.toString()
            if(addr.ipTextV6Address != null)
                return addr.ipTextV6Address.toString()
            return "<None>"
        }

        private fun TimeStamp.toLocalDateTime(): LocalDateTime {
            val str = this.toString().subSequence(0,12)
            return LocalDateTime.parse(str, formatter)
        }

        private fun List<ChangeOfCharCondition>.getDataVolumeGPRSUplink()
            = this.map { it.dataVolumeGPRSUplink.longValue() }.sum()

        private fun List<ChangeOfCharCondition>.getDataVolumeGPRSDownlink()
            = this.map { it.dataVolumeGPRSDownlink.longValue() }.sum()

        fun createInstance(record: SGWRecord): EricssonLteRecord {
            return EricssonLteRecord(
                recordType = record.recordType.toString(),
                servedIMSI = record.servedIMSI.toString(),
                sGWAddress = gsnAddressToString(record.sgwAddress),
                chargingID = record.chargingID.longValue(),
                servingNodeAddress = gsnAddressToString(record.servingNodeAddress.gsnAddress[0]),
                dataVolumeGPRSUplink = record.listOfTrafficVolumes?.changeOfCharCondition
                    ?.getDataVolumeGPRSUplink() ?: 0,
                dataVolumeGPRSDownlink = record.listOfTrafficVolumes?.changeOfCharCondition
                    ?.getDataVolumeGPRSDownlink() ?: 0,
                accessPointNameNI = record.accessPointNameNI.toString(),
                pdpPDNType = record.pdpPDNType.toString(),
                servedPDPPDNAddress = ipAddressToString(record.servedPDPPDNAddress.ipAddress),
                recordOpeningTime = record.recordOpeningTime.toLocalDateTime(),
                duration = record.duration.longValue(),
                causeForRecClosing = record.causeForRecClosing.longValue().toString(),
                recordSequenceNumber = record.recordSequenceNumber.longValue(),
                nodeID = record.nodeID.toString(),
                localSequenceNumber = record.localSequenceNumber.longValue(),
                servedMSISDN = record.servedMSISDN.toString(),
                chargingCharacteristics = record.chargingCharacteristics.toString(),
                servingNodePLMNIdentifier = record.servingNodePLMNIdentifier.toString(),
                servedIMEISV = record.servedIMEISV.toString(),
                rATType = record.ratType.longValue(),
                mSTimeZone = record.msTimeZone.toString(),
                sGWChange = if(record.sgwChange.value) 1 else 0,
                servingNodeType = record.servingNodeType.servingNodeType[0].longValue(),
                pGWAddressUsed = gsnAddressToString(record.pgwAddressUsed),
                pGWPLMNIdentifier = record.pgwplmnIdentifier.toString(),
                pDNConnectionID = record.pdnConnectionID.toString()
            )
        }
    }

    fun add(s: PreparedStatement){
        s.setString(1, recordType)
        s.setString(2, servedIMSI)
        s.setString(3, sGWAddress)
        s.setLong(4, chargingID)
        s.setString(5, servingNodeAddress)
        s.setString(6, pdpPDNType)
        s.setString(7, servedPDPPDNAddress)
        s.setTimestamp(8, java.sql.Timestamp( recordOpeningTime.toEpochSecond(ZoneOffset.UTC)))
        s.setLong(9, duration)
        s.setString(10, causeForRecClosing)
        s.setLong(11, recordSequenceNumber)
        s.setString(12, nodeID)
        s.setLong(13, localSequenceNumber)
        s.setString(13, servedMSISDN)
        s.setString(14, chargingCharacteristics)
        s.setString(15, servingNodePLMNIdentifier)
        s.setString(16, servedIMEISV)
        s.setLong(17, rATType)
        s.setString(18, mSTimeZone)
        s.setLong(19, sGWChange)
        s.setLong(20, servingNodeType)
        s.setString(21, pGWAddressUsed)
        s.setString(22, pGWPLMNIdentifier)
        s.setString(23, pDNConnectionID)
        s.addBatch()
    }
}