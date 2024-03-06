#include "mainwindow.h"
#include "ShowUsersWindow.h"
#include <QApplication>

using namespace std;
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    MainWindow* mainWindow = nullptr;
    int parametr = atoi(argv[1]);
    if(argc>1 && parametr == 0){
            ShowUsersWindow showUsersWindow;
            showUsersWindow.exec();
    }
    else{
        mainWindow = new MainWindow;
        mainWindow->show();
    }
    int result = a.exec();
    delete mainWindow;
    return result;
}
