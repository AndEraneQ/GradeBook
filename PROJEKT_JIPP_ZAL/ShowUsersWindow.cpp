#include "ShowUsersWindow.h"
#include "ui_showuserswindow.h"
#include <QMessageBox>
#include "person.h"
#include "fileData.h"
ShowUsersWindow::ShowUsersWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::ShowUsersWindow)
{
    ui->setupUi(this);
    ui->showUsersPlainTextEdit->setReadOnly(true);

    std::ifstream inputFile(directoryName + "usersdata.bin", std::ios::binary);
    try {
        Person *currentUser = new Person;
        while(currentUser->read(inputFile) && !inputFile.eof()){
            ui->showUsersPlainTextEdit->appendPlainText(QString::fromStdString("Rola: " + currentUser->getRole() + " Imie: " + currentUser->getName()));
            ui->showUsersPlainTextEdit->insertPlainText(QString::fromStdString(" Nazwisko: " + currentUser->getSurname() + " Email: "  + currentUser->getEmail()));
        }
        delete currentUser;
    }
    catch (std::exception& e) {
        QMessageBox::warning(this,"Reading data error",e.what());
    }
    inputFile.close();
}

ShowUsersWindow::~ShowUsersWindow()
{
    delete ui;
}
