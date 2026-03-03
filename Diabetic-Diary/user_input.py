from datetime import datetime

from globals import DATE_INPUT_FORMAT_STR


def input_int(msg, low_lim, high_lim):
    """
    Gets a number from the user and deals with input errors.
    :param msg: message for the user
    :type msg: str
    :param low_lim: lower limit
    :type low_lim: int
    :param high_lim: higher limit
    :type high_lim: int
    :return: decimal integer
    :rtype int
    """
    while True:
        try:
            number = int(input(msg))
            if low_lim <= number <= high_lim:
                return number
            else:
                print("Wystąpił błąd. Spróbuj jeszcze raz.")
        except ValueError:
            print("Wystąpił błąd. Spróbuj jeszcze raz.")


def input_date(msg):
    """
    Gets date from the user and deals with input errors.
    :param msg: message for the user
    :type msg: str
    :return: date object
    :rtype datetime
    """
    while True:
        try:
            date_start = input(msg)
            date_object = datetime.strptime(date_start, DATE_INPUT_FORMAT_STR)
            return date_object
        except ValueError:
            print("Wystąpił błąd. Spróbuj jeszcze raz.")