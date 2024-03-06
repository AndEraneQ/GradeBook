#ifndef STUDENT_H
#define STUDENT_H

#include "person.h"
#include "comments.h"

class Student : public Person
{
public:
    vector<int> grades;         /**< Vector storing the grades of the student. */
    vector<Comments*> comments; /**< Vector storing comments associated with the student. */

    /**
     * @brief Default constructor for the Student class.
     */
    Student();

    /**
     * @brief Parameterized constructor for the Student class.
     * @param p1 The base Person object.
     */
    Student(Person p1);

    /**
     * @brief Template function to calculate the average of elements in a vector.
     * @tparam T Type of elements in the vector.
     * @param elements The vector of elements.
     * @return The average of elements in the vector.
     */
    template <typename T>
    double calculateAverange(const std::vector<T>& elements)
    {
        if(elements.empty())
        {
            return 0.0;
        }
        double sum = 0.0;
        for(int i = 0; i < elements.size(); i++)
        {
            sum += elements[i];
        }
        return sum / elements.size();
    }
};

/**
 * @brief Read grades from a file stream.
 * @param file The input file stream.
 * @param grade Reference to store the read grade.
 * @return True if read successfully, false otherwise.
 */
bool readGrades(ifstream &file, int &grade);

#endif // STUDENT_H
