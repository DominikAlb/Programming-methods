import java.util.Scanner;

class BIN {
    public int[] tab;
    public int size;

    public BIN(int s) {
        size = s;
        tab = new int[size];
    }

    public void set(int s) {
        size = s;
        tab = new int[size];
    }

    public void display() {
        if (size != 0) System.out.print(tab[0]);
        for (int i = 1; i < size; i++){
            System.out.print("," + tab[i]);
        }
        System.out.println();
    }
}

class coins {
    static Scanner inp = new Scanner (System.in);
    static BIN bin_E;
    static BIN bin_H;
    static BIN bin_L;
    static BIN bin_G;
    static int K;
    static int N;

    static int pow3(int K) {
        int w = 1;
        for (int i = 1; i <= K; i++)
            w = w * 3;
        return w;
    }

    static void setK() {
        K = 2;
        int W = (pow3(K) - 3) / 2;
        while (N > W) {
            K++;
            W = (pow3(K) - 3) / 2;
        }
    }

    static void display(int[] left, int[] right, int size) {
        int prev = left[0];
        System.out.print(left[0]);
        boolean s = false;
        for (int i = 1; i < size; i++) {
            if (left[i] == prev + 1) {
                s = true;
                prev = left[i];
                if (i == size - 1) {
                    System.out.print("-" + prev);
                    break;
                }
            } else {
                if (s == true) {
                    System.out.print("-" + prev);
                    s = false;
                }
                System.out.print("," + left[i]);
                prev = left[i];
            }
        }
        System.out.print("_|_");
        prev = right[0];
        System.out.print(right[0]);
        s = false;
        for (int i = 1; i < size; i++) {
            if (right[i] == prev + 1) {
                s = true;
                prev = right[i];
                if (i == size - 1) {
                    System.out.print("-" + prev);
                    break;
                }
            } else {
                if (s == true) {
                    System.out.print("-" + prev);
                    s = false;
                }
                System.out.print("," + right[i]);
                prev = right[i];
            }
        }
        System.out.println();
    }

    static void nextWeighting() {
        if (bin_E.size == 0) {
            if (N >= pow3(K - 1) * 2) {
                int pk3 = pow3(K - 1);
                int l_w = 0;
                int r_w = 0;
                int g_w = 0;
                int[] left = new int[pk3];
                int[] right = new int[pk3];
                int h1;
                int h2;
                int l1;
                int l2;
                if (bin_H.size < bin_L.size) {
                    h1 = pk3 / 2;
                    h2 = 2 * h1;
                    l1 = pk3 - h1;
                    l2 = 2 * l1;
                } else {
                    l1 = pk3 / 2;
                    l2 = 2 * l1;
                    h1 = pk3 - l1;
                    h2 = 2 * h1;
                }
                // left
                if (bin_H.size >= h1) {
                    for (; l_w < h1; l_w++)
                        left[l_w] = bin_H.tab[l_w];
                } else {
                    for (; l_w < bin_H.size; l_w++)
                        left[l_w] = bin_H.tab[l_w];
                    for (; l_w < h1; l_w++)
                        left[l_w] = bin_G.tab[g_w++];
                }
                if (bin_L.size >= l1) {
                    for (int i = 0; i < l1; i++)
                        left[l_w++] = bin_L.tab[i];
                } else {
                    for (int i = 0; i < bin_L.size; i++)
                        left[l_w++] = bin_L.tab[i];
                    for (; l_w < pk3; l_w++)
                        left[l_w] = bin_G.tab[g_w++];
                }
                // right
                if (bin_H.size >= h2) {
                    for (int i = h1; i < h2; i++)
                        right[r_w++] = bin_H.tab[i];
                } else {
                    for (int i = h1; i < bin_H.size; i++)
                        right[r_w++] = bin_H.tab[i];
                    for (int i = 0; i < h2 - bin_H.size; i++)
                        right[r_w++] = bin_G.tab[g_w++];
                }
                if (bin_L.size >= l2) {
                    for (int i = l1; i < l2; i++)
                        right[r_w++] = bin_L.tab[i];
                } else {
                    for (int i = l1; i < bin_L.size; i++)
                        right[r_w++] = bin_L.tab[i];
                    for (; r_w < pk3; r_w++)
                        right[r_w] = bin_G.tab[g_w++];
                }
                display(left, right, pk3);
                char sign = inp.next().charAt(0);
                g_w = bin_G.size;
                if (sign == '>') {
                    int p = 0;
                    boolean mbad;
                    // BIN H
                    for (int i = 0; i < bin_H.size; i++) {
                        mbad = false;
                        for (int j = 0; j < pk3; j++) {
                            if (left[j] == bin_H.tab[i]) {
                                mbad = true;
                                bin_H.tab[i - p] = bin_H.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_H.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_H.size -= p;
                    // BIN L
                    p = 0;
                    for (int i = 0; i < bin_L.size; i++) {
                        mbad = false;
                        for (int j = 0; j < pk3; j++) {
                            if (right[j] == bin_L.tab[i]) {
                                mbad = true;
                                bin_L.tab[i - p] = bin_L.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_L.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_L.size -= p;
                } else if (sign == '<') {
                    int p = 0;
                    boolean mbad;
                    // BIN H
                    for (int i = 0; i < bin_H.size; i++) {
                        mbad = false;
                        for (int j = 0; j < pk3; j++) {
                            if (right[j] == bin_H.tab[i]) {
                                mbad = true;
                                bin_H.tab[i - p] = bin_H.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_H.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_H.size -= p;
                    // BIN L
                    p = 0;
                    for (int i = 0; i < bin_L.size; i++) {
                        mbad = false;
                        for (int j = 0; j < pk3; j++) {
                            if (left[j] == bin_L.tab[i]) {
                                mbad = true;
                                bin_L.tab[i - p] = bin_L.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_L.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_L.size -= p;
                } else if (sign == '=') {
                    int p = 0;
                    boolean mbad;
                    // BIN H
                    for (int i = 0; i < bin_H.size; i++) {
                        mbad = true;
                        for (int j = 0; j < pk3; j++) {
                            if (right[j] == bin_H.tab[i] || left[j] == bin_H.tab[i]) {
                                mbad = false;
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_H.tab[i];
                            bin_G.size++;
                        } else {
                            bin_H.tab[i - p] = bin_H.tab[i];
                        }
                    }
                    bin_H.size -= p;
                    // BIN L
                    p = 0;
                    for (int i = 0; i < bin_L.size; i++) {
                        mbad = true;
                        for (int j = 0; j < pk3; j++) {
                            if (left[j] == bin_L.tab[i] || right[j] == bin_L.tab[i]) {
                                mbad = false;
                                bin_L.tab[i - p] = bin_L.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_L.tab[i];
                            bin_G.size++;
                        } else {
                            bin_L.tab[i - p] = bin_L.tab[i];
                        }
                    }
                    bin_L.size -= p;
                }
                bin_G.size = g_w;
                K--;
            } else {
                int h1 = bin_H.size / 2;
                int h2 = bin_H.size / 2 + bin_H.size % 2;
                int l1 = h2;
                int l2 = h1;
                int[] left = new int[bin_H.size];
                int[] right = new int[bin_H.size];
                for (int i = 0; i < h1; i++) {
                    left[i] = bin_H.tab[i];
                    right[i] = bin_L.tab[i];
                }
                for (int i = h1; i < bin_H.size; i++) {
                    left[i] = bin_L.tab[i];
                    right[i] = bin_H.tab[i];
                }
                display(left, right, bin_H.size);

                char sign = inp.next().charAt(0);
                int g_w = bin_G.size;
                if (sign == '>') {
                    int p = 0;
                    boolean mbad;
                    // BIN H
                    for (int i = 0; i < bin_H.size; i++) {
                        mbad = false;
                        for (int j = 0; j < bin_H.size; j++) {
                            if (left[j] == bin_H.tab[i]) {
                                mbad = true;
                                bin_H.tab[i - p] = bin_H.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_H.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_H.size -= p;
                    // BIN L
                    p = 0;
                    for (int i = 0; i < bin_L.size; i++) {
                        mbad = false;
                        for (int j = 0; j < bin_L.size; j++) {
                            if (right[j] == bin_L.tab[i]) {
                                mbad = true;
                                bin_L.tab[i - p] = bin_L.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_L.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_L.size -= p;
                } else if (sign == '<') {
                    int p = 0;
                    boolean mbad;
                    // BIN H
                    for (int i = 0; i < bin_H.size; i++) {
                        mbad = false;
                        for (int j = 0; j < bin_H.size; j++) {
                            if (right[j] == bin_H.tab[i]) {
                                mbad = true;
                                bin_H.tab[i - p] = bin_H.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_H.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_H.size -= p;
                    // BIN L
                    p = 0;
                    for (int i = 0; i < bin_L.size; i++) {
                        mbad = false;
                        for (int j = 0; j < bin_L.size; j++) {
                            if (left[j] == bin_L.tab[i]) {
                                mbad = true;
                                bin_L.tab[i - p] = bin_L.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_L.tab[i];
                            bin_G.size++;
                        }
                    }
                    bin_L.size -= p;
                } else if (sign == '=') {
                    int p = 0;
                    boolean mbad;
                    // BIN H
                    for (int i = 0; i < bin_H.size; i++) {
                        mbad = true;
                        for (int j = 0; j < bin_H.size; j++) {
                            if (right[j] == bin_H.tab[i] || left[j] == bin_H.tab[i]) {
                                mbad = false;
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_H.tab[i];
                            bin_G.size++;
                        } else {
                            bin_H.tab[i - p] = bin_H.tab[i];
                        }
                    }
                    bin_H.size -= p;
                    // BIN L
                    p = 0;
                    for (int i = 0; i < bin_L.size; i++) {
                        mbad = true;
                        for (int j = 0; j < bin_L.size; j++) {
                            if (left[j] == bin_L.tab[i] || right[j] == bin_L.tab[i]) {
                                mbad = false;
                                bin_L.tab[i - p] = bin_L.tab[i];
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[g_w++] = bin_L.tab[i];
                            bin_G.size++;
                        } else {
                            bin_L.tab[i - p] = bin_L.tab[i];
                        }
                    }
                    bin_L.size -= p;
                }
                bin_G.size = g_w;
                K--;
            }
        } else {
            int pk3 = pow3(K - 1);
            if (bin_E.size >= pk3) {
                int X_end = pk3 / 2;
                int[] left = new int[X_end + 1];
                int[] right = new int[X_end + 1];
                for (int i = 0; i < X_end; i++)
                    left[i] = bin_E.tab[i];
                for (int i = 0; i < X_end + 1; i++)
                    right[i] = bin_E.tab[X_end +  i];
                left[X_end] = bin_G.tab[0];

                display(left, right, X_end + 1);

                char sign = inp.next().charAt(0);
                if (sign == '>') {
                    bin_H.tab = new int[X_end];
                    int o = 0;
                    for (int i = 0; i < X_end; i++) {
                        if (i + o >= X_end) break;
                        if (left[i + o] != bin_G.tab[0])
                            bin_H.tab[i] = left[i];
                        else o++;
                    }
                    bin_H.size = X_end - o;
                    bin_L.tab = new int[X_end + 1];
                    o = 0;
                    for (int i = 0; i < X_end + 1; i++) {
                        if (i + o >= X_end + 1) break;
                        if (right[i + o] != bin_G.tab[0])
                            bin_L.tab[i] = right[i];
                        else o++;
                    }
                    bin_L.size = X_end + 1 - o;
                    bin_E.tab = new int[0];
                    bin_E.size = 0;
                }
                if (sign == '<') {
                    bin_L.tab = new int[X_end];
                    int o = 0;
                    for (int i = 0; i < X_end; i++) {
                        if (i + o >= X_end) break;
                        if (left[i + o] != bin_G.tab[0])
                            bin_L.tab[i] = left[i];
                        else o++;
                    }
                    bin_L.size = X_end - o;
                    bin_H.tab = new int[X_end + 1];
                    o = 0;
                    for (int i = 0; i < X_end + 1; i++) {
                        if (i + o > X_end) break;
                        if (right[i + o] != bin_G.tab[i])
                            bin_H.tab[i] = right[i];
                        else o++;
                    }
                    bin_H.size = X_end + 1 - o;
                    bin_E.tab = new int[0];
                    bin_E.size = 0;
                }
                if (sign == '=') {
                    int p = 0;
                    boolean mbad;
                    for (int i = 0; i < bin_E.size; i++) {
                        mbad = true;
                        for (int j = 0; j < X_end; j++) {
                            if (right[j] == bin_E.tab[i] || left[j] == bin_E.tab[i]) {
                                mbad = false;
                            }
                        }
                        if (bin_E.tab[i] == right[X_end])
                            mbad = false;
                        if (mbad == false) {
                            p++;
                            bin_G.tab[bin_G.size++] = bin_E.tab[i];
                        } else {
                            bin_E.tab[i - p] = bin_E.tab[i];
                        }
                    }
                    bin_E.size -= p;
                }
            } else {
                int X_end;
                int[] left;
                int[] right;
                if (bin_E.size % 2 != 0) {
                    X_end = bin_E.size / 2 + 1;
                    left = new int[X_end];
                    right = new int[X_end];
                    for (int i = 0; i < X_end - 1; i++) {
                        left[i] = bin_E.tab[i];
                        right[i] = bin_E.tab[X_end + i];
                    }
                    left[X_end - 1] = bin_E.tab[X_end - 1];
                    right[X_end - 1] = bin_G.tab[0];
                } else {
                    X_end = bin_E.size / 2;
                    left = new int[X_end];
                    right = new int[X_end];
                    for (int i = 0; i < X_end; i++) {
                        left[i] = bin_E.tab[i];
                        right[i] = bin_E.tab[X_end + i];
                    }
                }

                display(left, right, X_end);

                char sign = inp.next().charAt(0);
                if (sign == '>') {
                    bin_H.tab = new int[X_end];
                    for (int i = 0; i < X_end; i++)
                        bin_H.tab[i] = left[i];
                    bin_H.size = X_end;
                    bin_L.tab = new int[X_end];
                    int o = 0;
                    for (int i = 0; i < X_end; i++) {
                        if (i + o >= X_end) break;
                        if (right[i + o] != bin_G.tab[0])
                            bin_L.tab[i] = right[i];
                        else o++;
                    }
                    bin_L.size = X_end - o;
                    bin_E.tab = new int[0];
                    bin_E.size = 0;
                }
                if (sign == '<') {
                    bin_L.tab = new int[X_end];
                    for (int i = 0; i < X_end; i++)
                        bin_L.tab[i] = left[i];
                    bin_L.size = X_end;
                    bin_H.tab = new int[X_end];
                    int o = 0;
                    for (int i = 0; i < X_end; i++) {
                        if (i + o >= X_end) break;
                        if (right[i + o] != bin_G.tab[0])
                            bin_H.tab[i] = right[i];
                        else o++;
                    }
                    bin_H.size = X_end - o;
                    bin_E.tab = new int[0];
                    bin_E.size = 0;
                }
                if (sign == '=') {
                    int p = 0;
                    boolean mbad;
                    for (int i = 0; i < bin_E.size; i++) {
                        mbad = true;
                        for (int j = 0; j < X_end; j++) {
                            if (right[j] == bin_E.tab[i] || left[j] == bin_E.tab[i]) {
                                mbad = false;
                            }
                        }
                        if (mbad == false) {
                            p++;
                            bin_G.tab[bin_G.size++] = bin_E.tab[i];
                        } else {
                            bin_E.tab[i - p] = bin_E.tab[i];
                        }
                    }
                    bin_E.size -= p;
                }
            }
            K--;
        }
    }

    static void firstWeighting() {
        int X_end = N / 3;
        int Y_end = 2 * X_end;

        System.out.print(1 + "-" + X_end);
        System.out.print(" _|_ ");
        System.out.print(X_end + 1 + "-" + Y_end);
        System.out.println();

        char sign = inp.next().charAt(0);

        if (sign == '>') {
            bin_H = new BIN(X_end);
            bin_L = new BIN(X_end);
            bin_E = new BIN(0);
            bin_G = new BIN(N);
            bin_G.size = N - Y_end;

            for (int i = 1; i <= X_end; i++) {
                bin_H.tab[i - 1] = i;
                bin_L.tab[i - 1] = X_end + i;
            }
            for (int i = Y_end + 1; i <= N; i++)
                bin_G.tab[i - Y_end - 1] = i;

        } else if (sign == '<') {
            bin_H = new BIN(X_end);
            bin_L = new BIN(X_end);
            bin_E = new BIN(0);
            bin_G = new BIN(N);
            bin_G.size = N - Y_end;

            for (int i = 1; i <= X_end; i++) {
                bin_L.tab[i - 1] = i;
                bin_H.tab[i - 1] = X_end + i;
            }
            for (int i = Y_end + 1; i <= N; i++)
                bin_G.tab[i - Y_end - 1] = i;

        } else if (sign == '=') {
            bin_H = new BIN(0);
            bin_L = new BIN(0);
            bin_E = new BIN(N - Y_end);
            bin_G = new BIN(N);
            bin_G.size = 2 * X_end;

            for (int i = 0; i < 2 * X_end; i++)
                bin_G.tab[i] = i + 1;
            for (int i = 0; i < N - Y_end; i++)
                bin_E.tab[i] = Y_end + i + 1;
        }
        K--;
    }

    static public void main(String[] Args) {
        System.out.print("N: ");
        N = inp.nextInt();
        if (N < 3) {
            System.out.print("N > 3!!!");
            return;
        }
        setK();
        //System.out.println("K = " + K);
        firstWeighting();
        while (!((bin_H.size == 1 && bin_L.size == 0) || (bin_H.size == 0 && bin_L.size == 1))) {
            if(bin_E.size == 0 && bin_H.size == 0 && bin_L.size == 0) {
                System.out.println("==> - <==");
                return;
            }
            /*System.out.print("E: ");
            bin_E.display();
            System.out.print("H: ");
            bin_H.display();
            System.out.print("L: ");
            bin_L.display();
            System.out.print("G: ");
            bin_G.display();*/
            nextWeighting();
        }
        /*System.out.print("E: ");
        bin_E.display();
        System.out.print("H: ");
        bin_H.display();
        System.out.print("L: ");
        bin_L.display();
        System.out.print("G: ");
        bin_G.display();*/
        if (bin_H.size == 1) {
            System.out.println("==> " + bin_H.tab[0] + " C" + " <==");
        } else {
            System.out.println("==> " + bin_L.tab[0] + " L" +" <==");
        }
    }
}
