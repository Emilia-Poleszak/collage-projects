from glucose_test import GlucoseTest
from globals import *


def save_glucose_tests(tests):
    """
    Saves glucose tests data to csv file.
    :param tests: list of glucose test objects
    :type tests: list[GlucoseTest]
    """
    output = ""
    for i in range(0, len(tests), 1):
        output += tests[i].to_csv_row()
    with open(DATA_FILE_NAME, "w") as text_file:
        text_file.write(output)


def read_glucose_tests():
    """
    Reads glucose tests data from csv file.
    :return: list of glucose test objects
    :rtype list[GlucoseType]
    """
    try:
        with open(DATA_FILE_NAME, "r") as text_file:
            data = text_file.read()
        rows = data.split("\n")
        tests_list = []

        for i in range(len(rows)):
            if "," in rows[i]:
                tests_list.append(GlucoseTest.from_csv_row(rows[i]))

        return tests_list
    except FileNotFoundError:
        return []