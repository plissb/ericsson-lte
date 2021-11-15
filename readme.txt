# build project
./gradlew build

#run parser
java -cp build/classes/java/main:lib/* ru.cwt.asn1.ericsson.EricssonAsn1Parser <file>

Example:
java -cp build/classes/java/main:lib/* ru.cwt.asn1.ericsson.EricssonAsn1Parser files/LIMO3EPG01-SGW_20210830125454_1.dat




