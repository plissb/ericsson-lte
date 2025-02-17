/**
 * This class file was automatically generated by jASN1 v1.10.0 (http://www.openmuc.org)
 */

package ru.cwt.asn1.ericsson.sgw_r9;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;


public class TimeReport implements BerType, Serializable {

	private static final long serialVersionUID = 1L;

	public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

	public byte[] code = null;
	private RatingGroupId ratingGroup = null;
	private TimeStamp startTime = null;
	private TimeStamp endTime = null;
	private DataVolumeGPRS dataVolumeUplink = null;
	private DataVolumeGPRS dataVolumeDownlink = null;
	
	public TimeReport() {
	}

	public TimeReport(byte[] code) {
		this.code = code;
	}

	public void setRatingGroup(RatingGroupId ratingGroup) {
		this.ratingGroup = ratingGroup;
	}

	public RatingGroupId getRatingGroup() {
		return ratingGroup;
	}

	public void setStartTime(TimeStamp startTime) {
		this.startTime = startTime;
	}

	public TimeStamp getStartTime() {
		return startTime;
	}

	public void setEndTime(TimeStamp endTime) {
		this.endTime = endTime;
	}

	public TimeStamp getEndTime() {
		return endTime;
	}

	public void setDataVolumeUplink(DataVolumeGPRS dataVolumeUplink) {
		this.dataVolumeUplink = dataVolumeUplink;
	}

	public DataVolumeGPRS getDataVolumeUplink() {
		return dataVolumeUplink;
	}

	public void setDataVolumeDownlink(DataVolumeGPRS dataVolumeDownlink) {
		this.dataVolumeDownlink = dataVolumeDownlink;
	}

	public DataVolumeGPRS getDataVolumeDownlink() {
		return dataVolumeDownlink;
	}

	public int encode(OutputStream reverseOS) throws IOException {
		return encode(reverseOS, true);
	}

	public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				reverseOS.write(code[i]);
			}
			if (withTag) {
				return tag.encode(reverseOS) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		int sublength;

		if (dataVolumeDownlink != null) {
			sublength = dataVolumeDownlink.encode(reverseOS, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(reverseOS, sublength);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 5
			reverseOS.write(0xA5);
			codeLength += 1;
		}
		
		if (dataVolumeUplink != null) {
			sublength = dataVolumeUplink.encode(reverseOS, true);
			codeLength += sublength;
			codeLength += BerLength.encodeLength(reverseOS, sublength);
			// write tag: CONTEXT_CLASS, CONSTRUCTED, 4
			reverseOS.write(0xA4);
			codeLength += 1;
		}
		
		sublength = endTime.encode(reverseOS, true);
		codeLength += sublength;
		codeLength += BerLength.encodeLength(reverseOS, sublength);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 3
		reverseOS.write(0xA3);
		codeLength += 1;
		
		sublength = startTime.encode(reverseOS, true);
		codeLength += sublength;
		codeLength += BerLength.encodeLength(reverseOS, sublength);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
		reverseOS.write(0xA2);
		codeLength += 1;
		
		sublength = ratingGroup.encode(reverseOS, true);
		codeLength += sublength;
		codeLength += BerLength.encodeLength(reverseOS, sublength);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 1
		reverseOS.write(0xA1);
		codeLength += 1;
		
		codeLength += BerLength.encodeLength(reverseOS, codeLength);

		if (withTag) {
			codeLength += tag.encode(reverseOS);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		if (totalLength == -1) {
			subCodeLength += berTag.decode(is);

			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
				codeLength += length.decode(is);
				ratingGroup = new RatingGroupId();
				subCodeLength += ratingGroup.decode(is, true);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
				codeLength += length.decode(is);
				startTime = new TimeStamp();
				subCodeLength += startTime.decode(is, true);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 3)) {
				codeLength += length.decode(is);
				endTime = new TimeStamp();
				subCodeLength += endTime.decode(is, true);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 4)) {
				codeLength += length.decode(is);
				dataVolumeUplink = new DataVolumeGPRS();
				subCodeLength += dataVolumeUplink.decode(is, true);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 5)) {
				codeLength += length.decode(is);
				dataVolumeDownlink = new DataVolumeGPRS();
				subCodeLength += dataVolumeDownlink.decode(is, true);
				subCodeLength += berTag.decode(is);
			}
			int nextByte = is.read();
			if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
			|| nextByte != 0) {
				if (nextByte == -1) {
					throw new EOFException("Unexpected end of input stream.");
				}
				throw new IOException("Decoded sequence has wrong end of contents octets");
			}
			codeLength += subCodeLength + 1;
			return codeLength;
		}

		codeLength += totalLength;

		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
			subCodeLength += length.decode(is);
			ratingGroup = new RatingGroupId();
			subCodeLength += ratingGroup.decode(is, true);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
			subCodeLength += length.decode(is);
			startTime = new TimeStamp();
			subCodeLength += startTime.decode(is, true);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 3)) {
			subCodeLength += length.decode(is);
			endTime = new TimeStamp();
			subCodeLength += endTime.decode(is, true);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 4)) {
			subCodeLength += length.decode(is);
			dataVolumeUplink = new DataVolumeGPRS();
			subCodeLength += dataVolumeUplink.decode(is, true);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
			subCodeLength += berTag.decode(is);
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 5)) {
			subCodeLength += length.decode(is);
			dataVolumeDownlink = new DataVolumeGPRS();
			subCodeLength += dataVolumeDownlink.decode(is, true);
			if (subCodeLength == totalLength) {
				return codeLength;
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS, false);
		code = reverseOS.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (ratingGroup != null) {
			sb.append("ratingGroup: ").append(ratingGroup);
		}
		else {
			sb.append("ratingGroup: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (startTime != null) {
			sb.append("startTime: ").append(startTime);
		}
		else {
			sb.append("startTime: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (endTime != null) {
			sb.append("endTime: ").append(endTime);
		}
		else {
			sb.append("endTime: <empty-required-field>");
		}
		
		if (dataVolumeUplink != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("dataVolumeUplink: ").append(dataVolumeUplink);
		}
		
		if (dataVolumeDownlink != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("dataVolumeDownlink: ").append(dataVolumeDownlink);
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

