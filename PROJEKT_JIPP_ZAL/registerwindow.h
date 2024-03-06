#ifndef REGISTERWINDOW_H
#define REGISTERWINDOW_H
#include "person.h"
#include <Qdialog>
namespace Ui {
class RegisterWindow;
}

class RegisterWindow : public QDialog
{
    Q_OBJECT

public:
    explicit RegisterWindow(QWidget *parent = nullptr);
    bool registercheck(std::string imie, std::string nazwisko, std::string email, std::string haslo, std::string rola);
    void makeBinnaryFile(Person p1,string namefile);
    ~RegisterWindow();

private slots:
    void on_registerPushButton_clicked();

    void on_cancelPushButton_clicked();

private:
    Ui::RegisterWindow *ui;
};

#endif // REGISTERWINDOW_H
