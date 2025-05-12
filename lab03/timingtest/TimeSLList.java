package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        for (int x = 1000; x <= 128000; x *= 2) {
            Ns.addLast(x);
        }
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        while (opCounts.size() <= 8) {
            opCounts.addLast(10000);
        }
        SLList newSLL = new SLList();
        int M = 10000;
        int i = 0;
        for (int y = 1000; y <= 128000; y *= 2) {
            newSLL.addLast(y);
            Stopwatch SLsw = new Stopwatch();
            while (i < M * y) {
                newSLL.getLast();
                i += 1;
            }
            double timeE = SLsw.elapsedTime();
            times.addLast(timeE);
            }
            printTimingTable(Ns, times, opCounts);
        }
    }
