from datetime import datetime
from globals import *
from user_input import input_date, input_int


class GlucoseTest:
    """
    Class to represent a glucose test data.
    """

    def __init__(self, date_object, glucose_level):
        """
        :param date_object: date
        :type date_object: datetime
        :param glucose_level: glucose level
        :type glucose_level: int
        """
        self.date_object = date_object
        self.glucose_level = glucose_level

    def to_csv_row(self):
        """
        GlucoseTest method. Converts GlucoseTest object to csv format string row.
        :return: csv format string row
        :rtype: str
        """
        return self.date_object.strftime(DATE_INPUT_FORMAT_STR) + "," + str(self.glucose_level) + "\n"

    @staticmethod
    def from_csv_row(row_str):
        """
        GlucoseTest method. Converts csv format string to GlucoseTest object.
        :param row_str: csv format string
        :type row_str: str
        :return: GlucoseTest object
        :rtype: GlucoseTest
        """
        assert "," in row_str
        row = row_str.split(",")
        return GlucoseTest(datetime.strptime(row[0], DATE_INPUT_FORMAT_STR), int(row[1]))

    def to_human_str(self):
        """
        GlucoseTest method. Converts date object and glucose level variable to string.
        :return: glucose test data string
        :rtype: str
        """
        return "Data: " + self.date_object.strftime(DATE_INPUT_FORMAT_STR) \
            + " Poziom glukozy: " + str(self.glucose_level) + " [mg/dl]"


def user_input_add_new_test(tests):
    """
    Add new glucose test object to list of glucose test objects.
    :param tests: list of glucose test objects
    :type tests: list[GlucoseTest]
    """
    while True:
        date_object = input_date("Podaj datę badania (" + DATE_INPUT_FORMAT_STR_HUMAN + ")\n")
        glucose_level = input_int("Podaj poziom cukru [mg/dl]\n", 0, float('inf'))

        print("Wczytano dane: data: " + str(date_object) + ", poziom glukozy: " + str(glucose_level) + " [mg/dl]")
        print("Czy dane są poprawne?\n1 - tak\n2 - nie")

        number = input_int("Wybierz z dostępnych opcji (1 lub 2)\n", 1, 2)
        if number == 2:
            continue
        else:
            test = GlucoseTest(date_object, glucose_level)
            tests.append(test)
            print("Badanie zapisane pomyślnie.\n")
            break