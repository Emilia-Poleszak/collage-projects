from glucose_test import user_input_add_new_test
from file_utils import *
from user_input import input_int, input_date


def print_tests_from_period(glucose_list, start_date, end_date):
    """
    Prints glucose tests data form given period.
    :param glucose_list: list of glucose test objects
    :type glucose_list: list[GlucoseTest]
    :param end_date: end date
    :type end_date: datetime.datetime
    :param start_date: start date
    :type start_date: datetime.datetime
    """
    glucose_list_from_period = []
    for i in range(len(glucose_list)):
        if start_date <= glucose_list[i].date_object <= end_date:
            glucose_list_from_period.append(glucose_list[i])
    if len(glucose_list_from_period) == 0:
        print("Brak badań w podanym okresie.")
    else:
        for i in range(len(glucose_list_from_period)):
            print(glucose_list_from_period[i].to_human_str(), end=" ")
            if glucose_list[i].glucose_level > 99:
                print("- powyżej normy!", end=" ")
            print()
    print()


if __name__ == '__main__':
    glucose_tests = read_glucose_tests()

    while True:
        print("DZIENNIK BADAŃ POZIOMU GLUKOZY WE KRWI\n"
              "1. Nowe badanie\n"
              "2. Odczyt badania z danego okresu\n"
              "3. Zakończ program")

        number = input_int("Wybierz z dostępnych opcji (1, 2, 3)\n", 1, 3)
        if number == 1:
            user_input_add_new_test(glucose_tests)
            save_glucose_tests(glucose_tests)
        elif number == 2:
            date_object_start = input_date("Podaj datę początkową (" + DATE_INPUT_FORMAT_STR_HUMAN + ")\n")
            date_object_end = input_date("Podaj datę końcową (" + DATE_INPUT_FORMAT_STR_HUMAN + ")\n")
            print("Badania z okresu " + str(date_object_start) + " - " + str(date_object_end) + ": ")
            print_tests_from_period(glucose_tests, date_object_start, date_object_end)
        else:
            exit()