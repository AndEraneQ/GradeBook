/********************************************************************************
** Form generated from reading UI file 'showbehavior.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SHOWBEHAVIOR_H
#define UI_SHOWBEHAVIOR_H

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

class Ui_ShowBehavior
{
public:
    QVBoxLayout *verticalLayout_3;
    QHBoxLayout *horizontalLayout;
    QLabel *centralLabel;
    QHBoxLayout *horizontalLayout_3;
    QVBoxLayout *verticalLayout_2;
    QHBoxLayout *horizontalLayout_2;
    QLabel *gradesLabel;
    QSpacerItem *horizontalSpacer_4;
    QPlainTextEdit *gradesPlainTextEdit;
    QVBoxLayout *verticalLayout;
    QLabel *posNegLabel;
    QPlainTextEdit *posNegPlainTextEdit;
    QHBoxLayout *horizontalLayout_5;
    QSpacerItem *horizontalSpacer_5;
    QPushButton *cancelPushButton;

    void setupUi(QDialog *ShowBehavior)
    {
        if (ShowBehavior->objectName().isEmpty())
            ShowBehavior->setObjectName("ShowBehavior");
        ShowBehavior->resize(700, 383);
        ShowBehavior->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_3 = new QVBoxLayout(ShowBehavior);
        verticalLayout_3->setObjectName("verticalLayout_3");
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        centralLabel = new QLabel(ShowBehavior);
        centralLabel->setObjectName("centralLabel");
        centralLabel->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(centralLabel);

        horizontalLayout->setStretch(0, 4);

        verticalLayout_3->addLayout(horizontalLayout);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName("horizontalLayout_3");
        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName("verticalLayout_2");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        gradesLabel = new QLabel(ShowBehavior);
        gradesLabel->setObjectName("gradesLabel");
        gradesLabel->setStyleSheet(QString::fromUtf8("font: italic 18pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(gradesLabel);

        horizontalSpacer_4 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_2->addItem(horizontalSpacer_4);


        verticalLayout_2->addLayout(horizontalLayout_2);

        gradesPlainTextEdit = new QPlainTextEdit(ShowBehavior);
        gradesPlainTextEdit->setObjectName("gradesPlainTextEdit");
        gradesPlainTextEdit->setStyleSheet(QString::fromUtf8("font: italic 12pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout_2->addWidget(gradesPlainTextEdit);


        horizontalLayout_3->addLayout(verticalLayout_2);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        posNegLabel = new QLabel(ShowBehavior);
        posNegLabel->setObjectName("posNegLabel");
        posNegLabel->setStyleSheet(QString::fromUtf8("font: italic 18pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(posNegLabel);

        posNegPlainTextEdit = new QPlainTextEdit(ShowBehavior);
        posNegPlainTextEdit->setObjectName("posNegPlainTextEdit");
        posNegPlainTextEdit->setStyleSheet(QString::fromUtf8("font: italic 12pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(posNegPlainTextEdit);


        horizontalLayout_3->addLayout(verticalLayout);


        verticalLayout_3->addLayout(horizontalLayout_3);

        horizontalLayout_5 = new QHBoxLayout();
        horizontalLayout_5->setObjectName("horizontalLayout_5");
        horizontalSpacer_5 = new QSpacerItem(40, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout_5->addItem(horizontalSpacer_5);

        cancelPushButton = new QPushButton(ShowBehavior);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_5->addWidget(cancelPushButton);


        verticalLayout_3->addLayout(horizontalLayout_5);


        retranslateUi(ShowBehavior);

        QMetaObject::connectSlotsByName(ShowBehavior);
    } // setupUi

    void retranslateUi(QDialog *ShowBehavior)
    {
        ShowBehavior->setWindowTitle(QCoreApplication::translate("ShowBehavior", "Dialog", nullptr));
        centralLabel->setText(QString());
        gradesLabel->setText(QCoreApplication::translate("ShowBehavior", "Oceny:", nullptr));
        posNegLabel->setText(QCoreApplication::translate("ShowBehavior", "Pozytywne, Negatywne:", nullptr));
        cancelPushButton->setText(QCoreApplication::translate("ShowBehavior", "Anuluj", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ShowBehavior: public Ui_ShowBehavior {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SHOWBEHAVIOR_H
