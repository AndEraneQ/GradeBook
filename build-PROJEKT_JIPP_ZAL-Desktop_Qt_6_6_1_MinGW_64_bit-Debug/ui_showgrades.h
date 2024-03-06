/********************************************************************************
** Form generated from reading UI file 'showgrades.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SHOWGRADES_H
#define UI_SHOWGRADES_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPlainTextEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_ShowGrades
{
public:
    QVBoxLayout *verticalLayout;
    QLabel *centralLabel;
    QHBoxLayout *horizontalLayout_3;
    QPlainTextEdit *gradesPlainTextEdit;
    QPlainTextEdit *avaragePlainTextEdit;
    QPlainTextEdit *finalGradePlainTextEdit;
    QHBoxLayout *horizontalLayout_5;
    QHBoxLayout *horizontalLayout;
    QLabel *avarageOfavarageLabel;
    QLabel *sumOfAvarageOfavarageLabel;
    QHBoxLayout *horizontalLayout_2;
    QLabel *avarageOfGradesLabel;
    QLabel *sumOfAvarageOfGradesLabel;
    QHBoxLayout *horizontalLayout_4;
    QSpacerItem *horizontalSpacer;
    QPushButton *cancelPushButton;

    void setupUi(QDialog *ShowGrades)
    {
        if (ShowGrades->objectName().isEmpty())
            ShowGrades->setObjectName("ShowGrades");
        ShowGrades->resize(847, 374);
        ShowGrades->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout = new QVBoxLayout(ShowGrades);
        verticalLayout->setObjectName("verticalLayout");
        centralLabel = new QLabel(ShowGrades);
        centralLabel->setObjectName("centralLabel");
        centralLabel->setStyleSheet(QString::fromUtf8("font: italic 18pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(centralLabel);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        gradesPlainTextEdit = new QPlainTextEdit(ShowGrades);
        gradesPlainTextEdit->setObjectName("gradesPlainTextEdit");
        gradesPlainTextEdit->setStyleSheet(QString::fromUtf8("font: italic 12pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(gradesPlainTextEdit);

        avaragePlainTextEdit = new QPlainTextEdit(ShowGrades);
        avaragePlainTextEdit->setObjectName("avaragePlainTextEdit");
        avaragePlainTextEdit->setStyleSheet(QString::fromUtf8("font: italic 12pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(avaragePlainTextEdit);

        finalGradePlainTextEdit = new QPlainTextEdit(ShowGrades);
        finalGradePlainTextEdit->setObjectName("finalGradePlainTextEdit");
        finalGradePlainTextEdit->setStyleSheet(QString::fromUtf8("font: italic 12pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_3->addWidget(finalGradePlainTextEdit);


        verticalLayout->addLayout(horizontalLayout_3);

        horizontalLayout_5 = new QHBoxLayout();
        horizontalLayout_5->setObjectName("horizontalLayout_5");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        avarageOfavarageLabel = new QLabel(ShowGrades);
        avarageOfavarageLabel->setObjectName("avarageOfavarageLabel");
        avarageOfavarageLabel->setStyleSheet(QString::fromUtf8("font: italic 18pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(avarageOfavarageLabel);

        sumOfAvarageOfavarageLabel = new QLabel(ShowGrades);
        sumOfAvarageOfavarageLabel->setObjectName("sumOfAvarageOfavarageLabel");
        sumOfAvarageOfavarageLabel->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(sumOfAvarageOfavarageLabel);


        horizontalLayout_5->addLayout(horizontalLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        avarageOfGradesLabel = new QLabel(ShowGrades);
        avarageOfGradesLabel->setObjectName("avarageOfGradesLabel");
        avarageOfGradesLabel->setStyleSheet(QString::fromUtf8("font: italic 18pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(avarageOfGradesLabel);

        sumOfAvarageOfGradesLabel = new QLabel(ShowGrades);
        sumOfAvarageOfGradesLabel->setObjectName("sumOfAvarageOfGradesLabel");
        sumOfAvarageOfGradesLabel->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(sumOfAvarageOfGradesLabel);


        horizontalLayout_5->addLayout(horizontalLayout_2);


        verticalLayout->addLayout(horizontalLayout_5);

        horizontalLayout_4 = new QHBoxLayout();
        horizontalLayout_4->setObjectName("horizontalLayout_4");
        horizontalSpacer = new QSpacerItem(168, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_4->addItem(horizontalSpacer);

        cancelPushButton = new QPushButton(ShowGrades);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 18pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_4->addWidget(cancelPushButton);


        verticalLayout->addLayout(horizontalLayout_4);


        retranslateUi(ShowGrades);

        QMetaObject::connectSlotsByName(ShowGrades);
    } // setupUi

    void retranslateUi(QDialog *ShowGrades)
    {
        ShowGrades->setWindowTitle(QCoreApplication::translate("ShowGrades", "Dialog", nullptr));
        centralLabel->setText(QString());
        avarageOfavarageLabel->setText(QCoreApplication::translate("ShowGrades", "\305\232rednia ze \305\233rednich:", nullptr));
        sumOfAvarageOfavarageLabel->setText(QString());
        avarageOfGradesLabel->setText(QCoreApplication::translate("ShowGrades", "\305\232rednia z ocen: ", nullptr));
        sumOfAvarageOfGradesLabel->setText(QString());
        cancelPushButton->setText(QCoreApplication::translate("ShowGrades", "Cofnij", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ShowGrades: public Ui_ShowGrades {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SHOWGRADES_H
