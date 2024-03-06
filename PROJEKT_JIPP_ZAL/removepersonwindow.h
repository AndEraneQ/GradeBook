#ifndef REMOVEPERSONWINDOW_H
#define REMOVEPERSONWINDOW_H

#include <QDialog>

namespace Ui {
class RemovePersonWindow;
}

class RemovePersonWindow : public QDialog
{
    Q_OBJECT

public:
    explicit RemovePersonWindow(QWidget *parent = nullptr);
    ~RemovePersonWindow();

private slots:
    void on_cancelPushButton_clicked();

    void on_addPushButton_clicked();

private:
    Ui::RemovePersonWindow *ui;
};

#endif // REMOVEPERSONWINDOW_H
