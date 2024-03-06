#ifndef PERSON_H
#define PERSON_H

#include <vector>
#include <fstream>
#include <QDialog>
#include <QMessageBox>

using namespace std;
class Person
{
protected:
    size_t nameSize;        /**< Size of the name string. */
    string name;            /**< Person's name. */
    size_t surenameSize;    /**< Size of the surname string. */
    string surename;        /**< Person's surname. */
    size_t emailSize;       /**< Size of the email string. */
    string email;           /**< Person's email address. */
    size_t roleSize;        /**< Size of the role string. */
    string role;            /**< Person's role. */
    size_t passwordSize;    /**< Size of the password string. */
    string password;        /**< Person's password. */

public:
    /**
     * @brief Default constructor for the Person class.
     */
    Person();

    /**
     * @brief Parameterized constructor for the Person class.
     * @param name Person's name.
     * @param surname Person's surname.
     * @param email Person's email address.
     * @param password Person's password.
     * @param role Person's role.
     */
    Person(string name, string surename, string email, string password, string role);

    /**
     * @brief Destructor for the Person class.
     */
    ~Person(){};

    /**
     * @brief Getter for the person's name.
     * @return The person's name.
     */
    string getName();

    /**
     * @brief Getter for the person's surname.
     * @return The person's surname.
     */
    string getSurname();

    /**
     * @brief Getter for the person's role.
     * @return The person's role.
     */
    string getRole();

    /**
     * @brief Getter for the person's password.
     * @return The person's password.
     */
    string getPassword();

    /**
     * @brief Getter for the person's email address.
     * @return The person's email address.
     */
    string getEmail();

    /**
     * @brief Setter for the person's name.
     * @param name The new name to set.
     */
    void SetName(string name);

    /**
     * @brief Setter for the person's surname.
     * @param surname The new surname to set.
     */
    void SetSurename(string surname);

    /**
     * @brief Setter for the person's email address.
     * @param email The new email address to set.
     */
    void SetEmail(string email);

    /**
     * @brief Setter for the person's role.
     * @param role The new role to set.
     */
    void SetRole(string role);

    /**
     * @brief Setter for the person's password.
     * @param password The new password to set.
     */
    void SetPassword(string password);

    /**
     * @brief Write the person's information to a file stream.
     * @param file The output file stream.
     */
    void write(ofstream &file);

    /**
     * @brief Read the person's information from a file stream.
     * @param file The input file stream.
     * @return True if read successfully, false otherwise.
     */
    bool read(ifstream &file);

    /**
     * @brief Overloaded stream insertion operator for displaying a Person object in QMessageBox.
     * @param msgBox The QMessageBox to display the information.
     * @param p1 The Person object to display.
     * @return Reference to the modified QMessageBox.
     */
    friend QMessageBox &operator<<(QMessageBox &msgBox, const Person p1);

    /**
     * @brief Vector of subjects
     */
    std::vector<std::string> subjects{"Matematyka", "Polski", "Fizyka", "WF", "Biologia"};
};

/**
 * @brief Read the current user's information from a file and populate the Person object.
 * @param parent The parent widget for QMessageBox.
 * @param currentUser Reference to the Person object to be populated.
 * @param namefile The name of the file to read the current user's information.
 */
void readCurrentUser(QWidget *parent, Person &currentUser, string namefile);

#endif // PERSON_H
