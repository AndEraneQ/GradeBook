/********************************************************************************
** Form generated from reading UI file 'removepersonwindow.ui'
**
** Created by: Qt User Interface Compiler version 6.6.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_REMOVEPERSONWINDOW_H
#define UI_REMOVEPERSONWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QDialog>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_RemovePersonWindow
{
public:
    QVBoxLayout *verticalLayout_2;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout_2;
    QLabel *choosePersonLabel;
    QLabel *pictureLabel;
    QComboBox *personComboBox;
    QHBoxLayout *horizontalLayout;
    QSpacerItem *horizontalSpacer;
    QPushButton *cancelPushButton;
    QPushButton *addPushButton;

    void setupUi(QDialog *RemovePersonWindow)
    {
        if (RemovePersonWindow->objectName().isEmpty())
            RemovePersonWindow->setObjectName("RemovePersonWindow");
        RemovePersonWindow->resize(484, 287);
        RemovePersonWindow->setStyleSheet(QString::fromUtf8("background-color: rgb(90, 90, 90);"));
        verticalLayout_2 = new QVBoxLayout(RemovePersonWindow);
        verticalLayout_2->setObjectName("verticalLayout_2");
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName("verticalLayout");
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName("horizontalLayout_2");
        choosePersonLabel = new QLabel(RemovePersonWindow);
        choosePersonLabel->setObjectName("choosePersonLabel");
        choosePersonLabel->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout_2->addWidget(choosePersonLabel);

        pictureLabel = new QLabel(RemovePersonWindow);
        pictureLabel->setObjectName("pictureLabel");

        horizontalLayout_2->addWidget(pictureLabel);


        verticalLayout->addLayout(horizontalLayout_2);

        personComboBox = new QComboBox(RemovePersonWindow);
        personComboBox->setObjectName("personComboBox");
        personComboBox->setStyleSheet(QString::fromUtf8("font: italic 14pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        verticalLayout->addWidget(personComboBox);


        verticalLayout_2->addLayout(verticalLayout);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName("horizontalLayout");
        horizontalSpacer = new QSpacerItem(78, 20, QSizePolicy::Expanding, QSizePolicy::Minimum);

        horizontalLayout->addItem(horizontalSpacer);

        cancelPushButton = new QPushButton(RemovePersonWindow);
        cancelPushButton->setObjectName("cancelPushButton");
        cancelPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        cancelPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(cancelPushButton);

        addPushButton = new QPushButton(RemovePersonWindow);
        addPushButton->setObjectName("addPushButton");
        addPushButton->setCursor(QCursor(Qt::OpenHandCursor));
        addPushButton->setStyleSheet(QString::fromUtf8("font: italic 22pt \"Trebuchet MS\";\n"
"color: \"white\";"));

        horizontalLayout->addWidget(addPushButton);

        horizontalLayout->setStretch(0, 5);
        horizontalLayout->setStretch(1, 7);
        horizontalLayout->setStretch(2, 7);

        verticalLayout_2->addLayout(horizontalLayout);


        retranslateUi(RemovePersonWindow);

        QMetaObject::connectSlotsByName(RemovePersonWindow);
    } // setupUi

    void retranslateUi(QDialog *RemovePersonWindow)
    {
        RemovePersonWindow->setWindowTitle(QCoreApplication::translate("RemovePersonWindow", "Dialog", nullptr));
        choosePersonLabel->setText(QCoreApplication::translate("RemovePersonWindow", "Wybierz osobe: ", nullptr));
        pictureLabel->setText(QString());
        cancelPushButton->setText(QCoreApplication::translate("RemovePersonWindow", "Anuluj", nullptr));
        addPushButton->setText(QCoreApplication::translate("RemovePersonWindow", "Usu\305\204", nullptr));
    } // retranslateUi

};

namespace Ui {
    class RemovePersonWindow: public Ui_RemovePersonWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_REMOVEPERSONWINDOW_H
