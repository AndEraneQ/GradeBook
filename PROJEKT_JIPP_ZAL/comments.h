#ifndef COMMENTS_H
#define COMMENTS_H

#include <fstream>

class Comments
{
protected:
    std::string type;       /**< Type of the comment. */
    size_t typeSize;        /**< Size of the comment type string. */
    std::string comment;    /**< The actual comment. */
    size_t commentSize;     /**< Size of the comment string. */

public:
    /**
     * @brief Default constructor for the Comments class.
     */
    Comments();

    /**
     * @brief Parameterized constructor for the Comments class.
     * @param comment The comment text.
     * @param type The type of the comment.
     */
    Comments(std::string comment, std::string type);

    /**
     * @brief Virtual destructor for the Comments class.
     */
    virtual ~Comments();

    /**
     * @brief Virtual function to get the comment text.
     * @return The comment text.
     */
    virtual std::string getComment();

    /**
     * @brief Virtual function to get the comment type.
     * @return The comment type.
     */
    virtual std::string getType();
};

class GradeComment : public Comments
{
    int grade;   /**< The associated grade. */

public:
    /**
     * @brief Default constructor for the GradeComment class.
     */
    GradeComment();

    /**
     * @brief Parameterized constructor for the GradeComment class.
     * @param grade The associated grade.
     * @param comment The comment text.
     */
    GradeComment(int grade, std::string comment);

    /**
     * @brief Constructor for the GradeComment class using a base Comments object.
     * @param c1 The base Comments object.
     */
    GradeComment(Comments c1);

    /**
     * @brief Write the GradeComment information to a file stream.
     * @param file The output file stream.
     */
    void writeToFile(std::ofstream &file);

    /**
     * @brief Read the GradeComment information from a file stream.
     * @param file The input file stream.
     * @return True if read successfully, false otherwise.
     */
    bool readFromFile(std::ifstream &file);

    /**
     * @brief Getter for the associated grade.
     * @return The associated grade.
     */
    int getGrade();

    /**
     * @brief Overridden function to get the comment type.
     * @return The comment type.
     */
    std::string getType() override;

    /**
     * @brief Overridden function to get the comment text.
     * @return The comment text.
     */
    std::string getComment() override;

    /**
     * @brief Destructor for the GradeComment class.
     */
    ~GradeComment() override;
};

class PositiveOrNegativeComment : public Comments
{
    std::string positiveOrNegative;   /**< Tell us if the comment is positive or negative. */
    size_t positiveOrNegativeSize;    /**< Size of the positiveOrNegative string. */

public:
    /**
     * @brief Default constructor for the PositiveOrNegativeComment class.
     */
    PositiveOrNegativeComment();

    /**
     * @brief Constructor for the PositiveOrNegativeComment class using a base Comments object.
     * @param c1 The base Comments object.
     */
    PositiveOrNegativeComment(Comments c1);

    /**
     * @brief Parameterized constructor for the PositiveOrNegativeComment class.
     * @param comment The comment text.
     * @param positiveOrNegative Indicates whether the comment is positive or negative.
     */
    PositiveOrNegativeComment(std::string comment, std::string positiveOrNegative);

    /**
     * @brief Write the PositiveOrNegativeComment information to a file stream.
     * @param file The output file stream.
     */
    void writeToFile(std::ofstream &file);

    /**
     * @brief Read the PositiveOrNegativeComment information from a file stream.
     * @param file The input file stream.
     * @return True if read successfully, false otherwise.
     */
    bool readFromFile(std::ifstream &file);

    /**
     * @brief Getter for positiveOrNegative attribute.
     * @return Positive or negative sentiment of the comment.
     */
    std::string getPositiveOrNegative();

    /**
     * @brief Overridden function to get the comment type.
     * @return The comment type.
     */
    std::string getType() override;

    /**
     * @brief Overridden function to get the comment text.
     * @return The comment text.
     */
    std::string getComment() override;

    /**
     * @brief Destructor for the PositiveOrNegativeComment class.
     */
    ~PositiveOrNegativeComment() override;
};

#endif // COMMENTS_H
