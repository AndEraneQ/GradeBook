#include "removepersonwindow.h"
#include "ui_removepersonwindow.h"
#include "person.h"
#include <vector>
#include <QFile>
#include "fileData.h"
vector<Person> allUsers{};
RemovePersonWindow::RemovePersonWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::RemovePersonWindow)
{
    allUsers.clear();
    ui->setupUi(this);
    QPixmap pix(":/1/a/removePersonWindowPicture.png");
    ui->pictureLabel->setPixmap(pix.scaled(150,150,Qt::KeepAspectRatio));
    std::ifstream inputFile(directoryName + "usersdata.bin", std::ios::binary);
    if(!inputFile.is_open()){
        QMessageBox::warning(this,"Error","Couldn't open a file");
    }
    try {
        Person user;
        int numberOfPerson=1;
        while(user.read(inputFile) && !inputFile.eof()){
            allUsers.push_back(user);
            ui->personComboBox->addItem(QString::fromStdString(std::to_string(numberOfPerson) + ". Imie: " + user.getName() + " Nazwisko: " + user.getSurname() + " Email: " + user.getEmail() + " Rola: " + user.getRole()));
            numberOfPerson++;
        }
    }
    catch(std::exception& e) {
        QMessageBox::warning(this,"err",e.what());
    }
    inputFile.close();
}

RemovePersonWindow::~RemovePersonWindow()
{
    delete ui;
}

void RemovePersonWindow::on_cancelPushButton_clicked()
{
    close();
}


void RemovePersonWindow::on_addPushButton_clicked()
{
    if(allUsers.size()==0){
        QMessageBox::information(this,"err","user not exist");
        return;
    }
    int numberOfDeletedPerson = ui->personComboBox->currentText().left(ui->personComboBox->currentText().indexOf('.')).toInt() - 1;

    QMessageBox *msgBox;
    msgBox = new QMessageBox(this);
    msgBox->setStyleSheet("QMessageBox { background-color: rgb(90, 90, 90); }"
                          "QMessageBox QLabel { font: italic 12pt 'Trebuchet MS'; color: white; }");
    msgBox->setIcon(QMessageBox::Information);
    msgBox->setWindowTitle("Usunięto osobę:");
    *msgBox << allUsers.at(numberOfDeletedPerson);
    msgBox->show();

    if(allUsers.at(numberOfDeletedPerson).getRole()=="Uczeń"){
        QString nameBinnaryFile;
        for(auto i=0u;i<allUsers.at(numberOfDeletedPerson).subjects.size();i++){
            nameBinnaryFile=QString::fromStdString(directoryName + allUsers.at(numberOfDeletedPerson).getName() + "." + allUsers.at(numberOfDeletedPerson).getSurname() + allUsers.at(numberOfDeletedPerson).subjects.at(i) +".bin");
            QFile::remove(nameBinnaryFile);
        }
        QFile::remove(QString::fromStdString(directoryName + allUsers.at(numberOfDeletedPerson).getName() + "." + allUsers.at(numberOfDeletedPerson).getSurname() + "behaviorGrade.bin"));
        QFile::remove(QString::fromStdString(directoryName + allUsers.at(numberOfDeletedPerson).getName() + "." + allUsers.at(numberOfDeletedPerson).getSurname() + "behaviorpORn.bin"));
    }
    allUsers.erase(allUsers.begin() + numberOfDeletedPerson);
    std::ofstream outputFile(directoryName + "usersdata.bin", std::ios::binary | std::ios::trunc);
    try {
        for(auto i=0u;i<allUsers.size();++i){
            allUsers.at(i).write(outputFile);
        }
        outputFile.close();
    } catch (std::exception &e) {
        QMessageBox::warning(this, "Error", e.what());
        return ;
    }
    allUsers.clear();
    close();
}

