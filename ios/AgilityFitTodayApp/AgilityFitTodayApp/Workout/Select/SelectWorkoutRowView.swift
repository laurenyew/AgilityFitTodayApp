//
//  SelectWorkoutRowView.swift
//  AgilityFitTodayApp
//
//  Created by laurenyew on 8/21/22.
//

import Foundation
import SwiftUI

struct SelectWorkoutRowView : View {
    let title: String
    let description: String
    
    var body: some View {
        HStack(alignment:.center) {
            Image("figure.run.circle.fill")
                .symbolRenderingMode(.monochrome)
                .resizable()
                .frame(width: 40, height: 40, alignment: Alignment.center)
                .scaledToFit()
                .padding(.init(top: 10, leading: 0, bottom: 10, trailing: 10))
            VStack(alignment:.leading){
                Text(title)
                    .font(.headline)
                Text(description)
                    .font(.body)
            }
            
        }
    }
}

struct SelectWorkoutRowView_Previews: PreviewProvider {
    static var previews: some View {
        SelectWorkoutRowView(
            title: "Cardio Treadmill",
            description: "Get ready to interval treadmill")
    }
}
