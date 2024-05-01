import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> {
    private List<T> list;

    public MergeSort(List<T> list) {
        this.list = list;
    }

    public List<T> start() {
        List<T> sortedList = new ArrayList<>(list);
        if (sortedList.size() < 2) {
            return sortedList;
        }
        List<T> temp = new ArrayList<>(sortedList);
        mergeSort(sortedList, temp, 0, sortedList.size() - 1);
        return sortedList;
    }

    private void mergeSort(List<T> list, List<T> temp, int leftStart, int rightEnd) {
        if (leftStart >= rightEnd) {
            return;
        }
        int middle = (leftStart + rightEnd) / 2;
        mergeSort(list, temp, leftStart, middle);
        mergeSort(list, temp, middle + 1, rightEnd);
    }
}
