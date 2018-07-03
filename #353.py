class Node:
    def __init__(self, data):
        self.data = data
        self.next = None
class LinkedList:
    def __init__(self):
        self.head = None
    def append(self, new_data):
        new_node = Node(new_data)
        if self.head is None:
            self.head = new_node
            return
        last = self.head
        while (last.next):
            last = last.next
        last.next =  new_node
    def printList(self):
        temp = self.head
        while (temp):
            print (temp.data,
            temp = temp.next)
def flip(List, i):
    temp = List.head
    while temp.next is not i or temp.next is not None:
        temp = temp.next
    tempIP = List.head.next
    temp.next = List.head
    if i is not None:
        List.head.next = i.next
    List.head = i
    if i is not None:
        i.next = tempIP
    
def main():
    n = int(input())
    List = LinkedList()
    for i in range(0, n):
        List.append(int(input()))
    xmax, xmax_idx, idx, x = List.head.data , List.head, List.head, List.head.data
    while idx is not None:
        if x > xmax:
            xmax, xmax_idx = x, idx
        idx = idx.next
    if xmax_idx is List.head:
        while xmax_idx.next is not None:
            xmax_idx = xmax_idx.next
        flip(List, xmax_idx)
    else:
        flip(List, xmax_idx)
    List.printList()
main()

