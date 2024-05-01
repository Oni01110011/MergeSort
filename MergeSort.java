import java.util.*;

/**
 * Eine generische Implementierung des Merge-Sort-Algorithmus.
 * Diese Klasse sortiert Elemente in einer Liste, die das Comparable-Interface implementieren.
 * @param <T> der Typ der Elemente in der Liste; muss vergleichbar sein
 * @author Bernhard Aichinger-Ganas
 * @version 2024-05-01
 */
public class MergeSort<T extends Comparable<T>> {
    private List<T> list;

    /**
     * Konstruktor, der den MergeSort mit einer spezifischen Liste initialisiert.
     * @param list Die Liste, die sortiert werden soll
     */
    public MergeSort(List<T> list) {
        this.list = list;
    }

    /**
     * Sortiert die bei der Konstruktion bereitgestellte Liste mithilfe des Merge-Sort-Algorithmus.
     * Die Sortierung basiert auf der natürlichen Ordnung der Elemente, wie sie durch ihre compareTo-Methode definiert ist.
     * @return eine neue Liste, die die sortierten Elemente enthält
     */
    public List<T> start() {
        List<T> sortedList = new ArrayList<>(list);
        if (sortedList.size() < 2) {
            return sortedList;
        }
        List<T> temp = new ArrayList<>(sortedList);
        mergeSort(sortedList, temp, 0, sortedList.size() - 1);
        return sortedList;
    }

    /**
     * Die rekursive Methode, die die Liste in Unterlisten aufteilt und diese einzeln sortiert,
     * bevor sie wieder zusammengeführt werden.
     * @param list Die zu sortierende Liste
     * @param temp Eine temporäre Liste zur Unterstützung des Zusammenführungsprozesses
     * @param leftStart Der Startindex der zu sortierenden Unterliste
     * @param rightEnd Der Endindex der zu sortierenden Unterliste
     */
    private void mergeSort(List<T> list, List<T> temp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }
        int middle = (leftStart + rightEnd) / 2;
        mergeSort(list, temp, leftStart, middle);
        mergeSort(list, temp, middle + 1, rightEnd);
        mergeHalves(list, temp, leftStart, rightEnd);
    }

    /**
     * Führt zwei sortierte Unterlisten zu einer einzigen sortierten Unterliste zusammen.
     * Die Unterlisten sind durch ihre Indizes innerhalb der größeren Liste definiert.
     * @param list Die ursprüngliche Liste, die die Unterlisten enthält, die zusammengeführt werden sollen
     * @param temp Eine temporäre Liste, um das zusammengeführte Ergebnis zu speichern
     * @param leftStart Der Startindex der ersten sortierten Unterliste
     * @param rightEnd Der Endindex der zweiten sortierten Unterliste
     */
    private void mergeHalves(List<T> list, List<T> temp, int leftStart, int rightEnd) {
        int leftEnd = (rightEnd + leftStart) / 2;
        int rightStart = leftEnd + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while (left <= leftEnd && right <= rightEnd) {
            if (list.get(left).compareTo(list.get(right)) <= 0) {
                temp.set(index, list.get(left));
                left++;
            } else {
                temp.set(index, list.get(right));
                right++;
            }
            index++;
        }

        for (int i = left; i <= leftEnd; i++, index++) {
            temp.set(index, list.get(i));
        }
        for (int i = right; i <= rightEnd; i++, index++) {
            temp.set(index, list.get(i));
        }
        for (int i = leftStart; i <= rightEnd; i++) {
            list.set(i, temp.get(i));
        }
    }
}
