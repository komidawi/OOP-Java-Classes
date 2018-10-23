public class PESELTester {

    public static void main(String[] args) {
        PESEL[] pesels = { new PESEL("50032094915"),
                        new PESEL("44051401358"),
                        new PESEL("02252578113")};

        for (PESEL pesel : pesels) {
            System.out.println(check(pesel));
        }
    }

    public static boolean check(PESEL pesel) {
        String number = pesel.getNumber();

        final int[] multipliers = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int sum = 0;

        for (int i = 0; i < number.length(); i++) {
            int currentDigit = Character.getNumericValue(number.charAt(i));
            sum += multipliers[i] * currentDigit;
        }

        if (sum % 10 == 0) {
            return true;
        } else {
            return false;
        }
    }
}