import re
import math
def oblicz(a, b, string, i):
    j = 2
    while j <= int(math.sqrt(a)):
        if a%j == 0 and j == 2:
            string += str(j)
            string += " * "
            a /= j
            j = 1
        j += 1
    if a == 1: 
        string += " )"
        while i > 0:
            string += " )"
            i -= 1
        print(string)
    elif a == 2:
        string += "2 )"
        while i > 0:
            string += " )"
            i -= 1
        print(string)
    else:
        string += "( 1 + "
        i += 1
        oblicz(a-1, 1, string, i)
        
        string += " )"

def main():
    line = int(input())
    oblicz(line, 1, "( ", 0)
main()