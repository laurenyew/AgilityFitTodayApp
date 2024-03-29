//
//  HomeView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 3/21/22.
//

import Foundation
import SwiftUI

struct HomeView: View {
    var body: some View {
        NavigationView {
            VStack {
                Image("IconLogo")
                    .resizable()
                    .frame(minWidth: 100, maxWidth: 200, minHeight: 100, maxHeight: 200, alignment: Alignment.center)
                    .scaledToFit()
                    .padding()
                Text("Welcome to the Agility Fit Today App!")
                    .padding()
                Text("This sample app features fitness workout creations that demo SwiftUI, Combine, async/await, CoreData, and other iOS tech/architecture")
                    .padding()
                NavigationLink(destination: SelectWorkoutView()){
                    Text("Start a Workout")
                }.padding()
                NavigationLink(destination: CreateWorkoutView()){
                    Text("Create a Workout")
                }.padding()
                NavigationLink(destination: WorkoutHistoryView()){
                    Text("Workout History")
                }.padding()
            }
            .navigationTitle("Home")
            .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
