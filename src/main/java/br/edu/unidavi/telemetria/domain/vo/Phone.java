package br.edu.unidavi.telemetria.domain.vo;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * Created by marcondesmacaneiro 12/07/16.
 */
public class Phone implements Serializable, Comparable<Phone> {

    private static final long serialVersionUID = 1L;

    public static final int MAX_LENGHT = 20;

    private final String areaCode;

    private final String number;

    private Phone(String areaCode, String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public static Phone of(String value) {
        checkNotNull(value, "Phone is required!");
        String digits = value.replaceAll("\\D", "");
        if(digits.matches("\\d{3}")){
            return new Phone(null, digits);
        }
        else{
            checkArgument(digits.matches("\\d{10,11}"), "Phone is invalid!");
            String areaCode = digits.substring(0, 2);
            String number = digits.substring(2);
            return new Phone(areaCode, number);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (isNull(obj)) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (obj instanceof Phone) {
            Phone other = (Phone) obj;
            return Objects.equal(areaCode, other.areaCode)
                    && Objects.equal(number, other.number);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(areaCode, number);
    }

    public String getAreaCode() {
        return areaCode;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        switch (number.length()) {
            case 3:
                return String.format("%s", number);
            case 8:
                return String.format("%s %s-%s",
                        areaCode,
                        number.substring(0, 4),
                        number.substring(4));
            default:
                return String.format("%s %s-%s",
                        areaCode,
                        number.substring(0, 5),
                        number.substring(5));
        }
    }

    @Override
    public int compareTo(Phone o) {
        return ComparisonChain.start()
                .compare(areaCode, o.areaCode)
                .compare(number, o.number)
                .result();
    }
}
