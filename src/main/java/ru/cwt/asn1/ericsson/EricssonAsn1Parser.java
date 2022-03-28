package ru.cwt.asn1.ericsson;

import com.quantum.soft.ericsson.lte.EricssonLteRecord;
import ru.cwt.asn1.ericsson.sgw_r9.GPRSRecord;
import ru.cwt.asn1.ericsson.sgw_r9.SGWRecord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EricssonAsn1Parser {

    static int cdrSize = 0x1000;

    public static void main(String[] args) throws IOException {
        String fileName = null;
        FileInputStream fis = null;

        List<EricssonLteRecord> records = new ArrayList<>();


        if (args.length != 1) {
            System.err.println("Usage: EricssonAsn1Parser <file_name>");
            System.exit(1);
        } else {
            fileName = args[0];
        }


        try {
            fis = new FileInputStream(fileName);
        }catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " isn't found");
            System.exit(1);
        }

        System.out.println("File: " + fileName);
        List<GPRSRecord> callEventRecordList = new ArrayList<>();

        int offset = 0;

        while (offset < fis.getChannel().size()) {
            try {
                GPRSRecord call = new GPRSRecord();
                if (fis.read() == 0x00) {
                    offset = ((offset / cdrSize) + 1) * cdrSize;
                }

                if (offset >= fis.getChannel().size()) {
                    continue;
                }
                fis.getChannel().position(offset);

                offset += call.decode(fis);
                records.add(EricssonLteRecord.Companion.createInstance(call.getSGWRecord()));

//                callEventRecordList.add(call);
//                System.out.println(call);
            } catch (Exception e) {
                System.out.println("File: " + fileName);
                e.printStackTrace(System.out);
                System.exit(1);
            }
        }
        System.out.println("CallEventRecords total: " + records.size() + "\n");
    }
}
