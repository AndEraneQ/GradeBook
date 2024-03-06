#ifndef ADDGRADES_H
#define ADDGRADES_H

#include <QDialog>

namespace Ui {
class AddGrades;
}

class AddGrades : public QDialog
{
    Q_OBJECT

public:
    explicit AddGrades(QWidget *parent = nullptr);
    ~AddGrades();

private slots:
    void on_addPushButton_clicked();

    void on_cancelPushButton_clicked();

private:
    Ui::AddGrades *ui;
};
#endif // ADDGRADES_H
