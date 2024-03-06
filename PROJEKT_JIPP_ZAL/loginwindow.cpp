#include "loginwindow.h"
#include "ui_loginwindow.h"
#include <QMessageBox>
#include <Qpixmap>
#include "person.h"
#include "fileData.h"

bool LoginWindow::loginCheck(string email,string haslo){
    if(email=="" || haslo==""){
        QMessageBox::warning(this,"Error","Complete full data");
        return false;
    }
    return true;
}

LoginWindow::LoginWindow(QWidget *parent)
    : QDialog(parent)
    , ui(new Ui::LoginWindow)
{
    ui->setupUi(this);
    QPixmap pix(":/1/a/loginWindowPicture");
    ui->label_3->setPixmap(pix.scaled(200,200,Qt::KeepAspectRatio));
}

void LoginWindow::on_cancelPushButton_clicked()
{
    this->close();
}

LoginWindow::~LoginWindow()
{
    delete ui;
    delete teacherWindow;
    delete studentWindow;
    delete adminWindow;
}

void LoginWindow::on_loginPushButton_clicked()
{
    string email = ui->emailLineEdit->text().toStdString();
    string haslo = ui->passwordLineEdit->text().toStdString();
    if((email=="admin" && haslo=="12345678")){
        this->close();
        adminWindow = new AdminWindow(this);
        adminWindow->show();
        return;
    }
    Person currentUser;
    bool userFound = false;
    if(loginCheck(email,haslo)){
        std::ifstream inputFile(directoryName + "usersdata.bin", std::ios::binary);
        try {
            while(currentUser.read(inputFile) && !inputFile.eof()){
                if(currentUser.getEmail()==email && currentUser.getPassword()==haslo){
                    QMessageBox::information(this,"Information","User found!");
                    userFound=true;
                    break;
                }
            }
        }
        catch (std::exception& e) {
            QMessageBox::warning(this,"Error",e.what());
        }
        inputFile.close();
        if(!userFound){
            QMessageBox::warning(this, "Error", "Invalid email or password");
            return;
        }
        std::ofstream outputFile(directoryName + "currentuser.bin",std::ios::binary);
        try{
            currentUser.write(outputFile);
        }
        catch (std::exception &e){
            QMessageBox::warning(this, "Error", e.what());
            return ;
        }
        outputFile.close();
        this->close();
        if(currentUser.getRole()=="Uczeń"){
            studentWindow = new StudentWindow(this);
            studentWindow->show();
        }
        else if(currentUser.getRole()=="Nauczyciel"){
            teacherWindow = new TeacherWindow(this);
            teacherWindow->show();
        }
    }
}
