#ifndef LOGINWINDOW_H
#define LOGINWINDOW_H

#include "teacherwindow.h"
#include "studentwindow.h"
#include "adminwindow.h"
#include <QDialog>

namespace Ui {
class LoginWindow;
}

class LoginWindow : public QDialog
{
    Q_OBJECT

public:
    explicit LoginWindow(QWidget *parent = nullptr);
    bool loginCheck(std::string email,std::string haslo);
    ~LoginWindow();
private slots:
    void on_cancelPushButton_clicked();

    void on_loginPushButton_clicked();

private:
    Ui::LoginWindow *ui;
    TeacherWindow *teacherWindow;
    StudentWindow *studentWindow;
    AdminWindow *adminWindow;
};
#endif // LOGINWINDOW_H
