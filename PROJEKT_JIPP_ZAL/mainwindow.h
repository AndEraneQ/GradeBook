#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include "loginwindow.h"
#include "registerwindow.h"
#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui {
class MainWindow;
}
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

private slots:
    void on_cancelPushButton_clicked();

    void on_loginPushButton_clicked();

    void on_registerPushButton_clicked();

private:
    Ui::MainWindow *ui;
    LoginWindow *loginWindow;
    RegisterWindow *registerWindow;
};
#endif // MAINWINDOW_H
