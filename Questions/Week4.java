package Questions;
public class Week4 {

    // function to sort an array

    private static void sortArray(int array[]) {

        // looping through the elements of the array provided in the parameter

        for (int i = 1; i < array.length; i++) {

            int j = i;

            int a = array[i];



            // comparing the current element with the previous element

            while ((j > 0) && (array[j - 1] > a)) {

                array[j] = array[j - 1]; // swapping the current element with the previous element

                j--; // decrement j

            }

            array[j] = a; // assigning the current element to the index where the previous element was

            // swapped

        }

    }
    public static void findTotalContainers(int array[]) {

        // sorting the array

        sortArray(array);



        int prevValue = 0; // storing element before the current element

        int containerForCurrentVillage = 0; // number of containers used for the current village

        int totalContainers = 0; // variable to store total number of containers



        // looping through the elements of the sorted array

        for (int i = 0; i < array.length; i++) {

            // execute if statement if the current element is not same as the previous

            // element

            if (array[i] != prevValue) {

                prevValue = array[i];

                containerForCurrentVillage += 1;

                totalContainers += containerForCurrentVillage;

            }

            // execute else statement if the curret element is same as the previous element

            else {

                prevValue = array[i];

                totalContainers += containerForCurrentVillage;

            }

        }



        System.out.println(totalContainers);

    }



    public static void main(String[] args) {

        // initializing an array

        int array[] = { 5, 2, 6 };



        // calling the function to find total number of containers

        findTotalContainers(array);

    }

}