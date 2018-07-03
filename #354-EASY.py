import re
import math

def oblicz(a, b):
    for i in range(int(math.sqrt(a)), 0 , -1):
        if a % i == 0:
            return print(a, " = " , int(a/i) , " * ",  int(b-(a/i)))

def main():
    string = input()
    line = re.findall('\d+', string)
    print(oblicz(int(line[0]), int(line[1])))
main()