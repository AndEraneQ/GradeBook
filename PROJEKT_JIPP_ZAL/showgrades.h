#ifndef SHOWGRADES_H
#define SHOWGRADES_H

#include <QDialog>

namespace Ui {
class ShowGrades;
}

class ShowGrades : public QDialog
{
    Q_OBJECT

public:
    explicit ShowGrades(QWidget *parent = nullptr);
    ~ShowGrades();

private slots:
    void on_cancelPushButton_clicked();

private:
    Ui::ShowGrades *ui;
};

#endif // SHOWGRADES_H
